package biblio.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import biblio.controller.LivreController;
import biblio.rest.controller.LivreRestController;

@Configuration
@ComponentScan(basePackageClasses = {LivreController.class, LivreRestController.class})
@EnableWebMvc
public class BiblioWebConfig implements WebMvcConfigurer {
	
	public BiblioWebConfig() {
		System.out.println("**** BiblioWebConfig ****");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// si vue logique = "adherents" --> "/WEB-INF/jsp/adherents.jsp"
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/jsp/");
			resolver.setSuffix(".jsp");
			registry.viewResolver(resolver);
	}

    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("jsp/home").setViewName("welcome");
    }
    
    @Bean
    public PropertiesFactoryBean users() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("users.properties")); 
        return bean;
    }
}

