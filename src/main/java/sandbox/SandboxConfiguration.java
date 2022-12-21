package sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan
//@PropertySource("classpath:/sandbox/sandbox-prod.properties")
public class SandboxConfiguration {

    public SandboxConfiguration() {
        System.out.println("*** *** SandboxConfiguration() ***");

    }

    /*@Bean
    public UnClient client(Fournisseur fournisseur) {
        UnClient client = new UnClient();
        client.setFournisseur(fournisseur);
        return client;
    }*/

    @Bean
    public UnFournisseur autreFournisseur(@Value("${autreFournisseur.nom}") String nom) {
        UnFournisseur fournisseur = new UnFournisseur();
        fournisseur.setNom(nom);
        return fournisseur;
    }

    @Bean
    public UnFournisseur fournisseur(@Value("${fournisseur.nom}") String nom) {
        UnFournisseur fournisseur = new UnFournisseur();
        fournisseur.setNom(nom);
        return fournisseur;
    }

   /* @Bean
    @Profile("dev")
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer_dev() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("/sandbox/sandbox-dev.properties"));

        return placeholderConfigurer;
    }*/

    /*@Bean
    @Profile("prod")
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer_prod() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("/sandbox/sandbox-prod.properties"));

        return placeholderConfigurer;
    }*/



}
