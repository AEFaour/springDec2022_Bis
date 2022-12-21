package biblio.config.persistence;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PersistencePropertiesConfig {
	
	@Autowired
	Environment environment;
	
	@Profile("dev")
	@Bean("biblioPersistenceProperties")
	public PropertiesFactoryBean persistencePropertiesDev() {
		System.err.println("**** active profiles = @Profile(\"dev\")"); 
	    PropertiesFactoryBean bean = new PropertiesFactoryBean();
	    bean.setLocation(new ClassPathResource("/META-INF/persistence-dev.properties"));
	    return bean;
	}
	
	@Profile("prod")
	@Bean("biblioPersistenceProperties")
	public PropertiesFactoryBean persistencePropertiesProd() {
		System.err.println("**** active profiles = @Profile(\"prod\")"); 
	    PropertiesFactoryBean bean = new PropertiesFactoryBean();
	    bean.setLocation(new ClassPathResource("/META-INF/persistence-prod.properties"));
	    return bean;
	}
	
	@Bean
	public Integer showProperties(@Qualifier("biblioPersistenceProperties")  PropertiesFactoryBean persistenceProperties) throws IOException {
		System.err.println("**** active profiles = " + environment.getProperty("spring.profiles.active"))	;
		System.err.println("**** javax.persistence.jdbc.url = " + persistenceProperties.getObject().getProperty("javax.persistence.jdbc.url"));
		return 1;
	}
	
}
