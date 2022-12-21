package biblio.rest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import biblio.config.BiblioConfig;
import biblio.config.BiblioWebConfig;
import biblio.entity.Adherent;
import biblio.service.Bibliotheque;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {BiblioConfig.class, BiblioWebConfig.class} )
@EnableWebMvc
@WebAppConfiguration
@ActiveProfiles("dev")
public class AdherentRestControllerTest {

    private MockMvc mockMvc;
    private Adherent adherent;

    @Autowired
    private Bibliotheque biblio;
    @Autowired
    private WebApplicationContext webApplicationContext;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public  void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        biblio.getAdherentRepository().deleteAllInBatch();
        adherent = new Adherent("Durant", "Pascal", "0240563412", "pascal.durant@free.fr");
        biblio.ajouterAdherent(adherent);
    }
    
    @Test
    public void essai() {
    	System.out.println("***** " + webApplicationContext.getServletContext().getContextPath() + " *****");
    }

    @Test
    public void getAdherentTest() throws Exception {
    	mockMvc.perform(get("/rest/adherents/{id}", adherent.getId())
    			.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(adherent.getId())))
                .andExpect(jsonPath("$.email", is(adherent.getEmail())));
    	
    	mockMvc.perform(get("/rest/adherents/"+ adherent.getId()*10))
        	   	.andExpect(status().isNotFound());
             
    }

    @Test
    public void createAdherentTest() throws Exception {
        String adherentJson = mapper.writeValueAsString(new Adherent("Durant", "Pascal", "0240563412", "pascal.durant@free.fr"));    
        mockMvc.perform(post("/rest/adherents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adherentJson))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string(containsString("BusinessException : BibliothequeImpl.ajouterAdherent")));
        adherentJson = mapper.writeValueAsString(new Adherent("Durand", "Georges", "0136253610", "georges.durand@gmail.com"));  
        mockMvc.perform(post("/rest/adherents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adherentJson))
                .andExpect(status().isOk());
        Assert.assertEquals(biblio.getAdherentRepository().findAll().size(), 2);
                
                
    }
    
    @Test
    public void updateAdherent() throws Exception {
    	adherent.setNom("Dupont");
        String adherentJson = mapper.writeValueAsString(adherent);    
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/adherents/" + adherent.getId()+200)
                .contentType(MediaType.APPLICATION_JSON)
                .content(adherentJson))
                .andExpect(status().isNotFound());
        mockMvc.perform(put("/rest/adherents/"+adherent.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(adherentJson))
                .andExpect(status().isOk());
        Assert.assertEquals(biblio.getAdherentRepository().findById(adherent.getId()).get().getNom(), "Dupont");          
                
    }
    
   @Test
    public void deleteAdherent() throws Exception {
    	for(Adherent a : biblio.getAdherentRepository().findAll())
    		mockMvc.perform(delete("/rest/adherents/"+a.getId()))
                	.andExpect(status().isOk()).andDo(print());
        Assert.assertEquals(biblio.getAdherentRepository().findAll().size(),0);          
                
    }

}
