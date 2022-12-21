package biblio.config;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import biblio.aop.LoggerInterceptor;
import biblio.repository.LivreRepository;
import biblio.service.impl.BibliothequeImpl;

@Configuration
@ComponentScan(basePackageClasses= {BibliothequeImpl.class,LoggerInterceptor.class})
@ComponentScan(basePackages = "/biblio/config/persistence")
@PropertySource(value="classpath:/spring/biblio.properties")
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackageClasses = LivreRepository.class)
@EnableTransactionManagement
public class BiblioConfig {

	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory( 
			Properties biblioPersistenceProperties) {
		LocalContainerEntityManagerFactoryBean emf = 
				new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		emf.setJpaProperties(biblioPersistenceProperties);
		emf.setPersistenceUnitName("biblio");
		return emf;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(emf);
		return tx;
	}
}






