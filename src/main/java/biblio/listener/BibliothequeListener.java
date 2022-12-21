package biblio.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import biblio.entity.Adherent;
import biblio.entity.Emprunt;
import biblio.entity.Livre;
import biblio.repository.AdherentRepository;
import biblio.repository.EmpruntRepository;
import biblio.repository.LivreRepository;
import biblio.service.Bibliotheque;



@WebListener
public class BibliothequeListener implements ServletContextListener {
	
    /**
     * Default constructor. 
     */
    public BibliothequeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    
    		
    	System.out.println("***** servletContext : " + event.getServletContext().getContextPath());
    	Bibliotheque biblio = applicationContext.getBean(Bibliotheque.class);
        LivreRepository livreRepo = biblio.getLivreRepository();
        AdherentRepository adherentRepo = biblio.getAdherentRepository();
        EmpruntRepository empruntRepo = biblio.getEmpruntRepository();
        
        empruntRepo.deleteAll(empruntRepo.findAll()); 
		livreRepo.deleteAll(livreRepo.findAll());  
		adherentRepo.deleteAll(adherentRepo.findAll()); 
		
		Adherent ad1 = new Adherent("Dupond", "Jean", "0234567812", "jean.dupont.@yahoo.fr");
 		Adherent ad2 = new Adherent("Durant", "Jacques", "0223674512", "jacques.durant@free.fr");
 		Adherent ad3 = new Adherent("Martin", "Bernadette", "0138792012", "m.bernadette@gmail.com");
 		
 		int idAd1 = biblio.ajouterAdherent(ad1);
 		int idAd2 = biblio.ajouterAdherent(ad2);
 		biblio.ajouterAdherent(ad3);
 		
 		Livre l1 = new Livre("Stupeur et tremblements",1999, "Amélie Nothomb");
 		Livre l2 = new Livre("L'étranger",1942, "Albert Camus");

 		int idL1 = biblio.ajouterLivre(l1);
 		int idL2 = biblio.ajouterLivre(l2);
 		
 		biblio.ajouterLivre(new Livre("Réglez-lui son compte !",1949, "Frédéric Dard"));
 		biblio.ajouterLivre(new Livre("Tintin au Tibet",1960, "Hergé"));
 		
 		empruntRepo.save(new Emprunt(livreRepo.findById(idL1).orElse(null), adherentRepo.findById(idAd1).orElse(null)));
 		empruntRepo.save(new Emprunt(livreRepo.findById(idL2).orElse(null), adherentRepo.findById(idAd2).orElse(null)));
		
 		event.getServletContext().setAttribute("biblio", biblio);	
         						
     
    }
	
}
