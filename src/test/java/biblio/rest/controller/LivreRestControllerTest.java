package biblio.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import biblio.config.BiblioConfig;
import biblio.config.BiblioWebConfig;
import biblio.entity.Livre;
import biblio.service.Bibliotheque;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {BiblioConfig.class, BiblioWebConfig.class} )
@EnableWebMvc 
@WebAppConfiguration
@ActiveProfiles("dev")
public class LivreRestControllerTest {

    private MockMvc mockMvc;
    private Livre livre;

    @Autowired private Bibliotheque biblio;
    @Autowired private WebApplicationContext webApplicationContext;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public  void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        livre = new Livre("Stupeur et tremblements",1999, "Amélie Nothomb");
        biblio.ajouterLivre(livre);
    }
    
    @Test
    public void contextPathTest() {
    	System.out.println("***** " + webApplicationContext.getServletContext().getContextPath() + " *****");
    }
    
    @Test
    public void getLivreTest() throws Exception {
    	mockMvc.perform(get("/rest/livres/{id}", livre.getId())
    			.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(livre.getId())))
                .andExpect(jsonPath("$.auteur", is(livre.getAuteur())));
    	mockMvc.perform(get("/rest/adherents/"+ livre.getId()*10))
        	   	.andExpect(status().isNotFound());            
    }
    // ... autres méthodes de test ...
}






