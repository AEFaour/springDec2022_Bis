package biblio.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import biblio.config.BiblioConfig;
import biblio.entity.Adherent;
import biblio.entity.Emprunt;
import biblio.entity.Livre;
import biblio.repository.AdherentRepository;
import biblio.repository.EmpruntRepository;
import biblio.repository.LivreRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=BiblioConfig.class)
@Transactional
@ActiveProfiles("dev")
public class TestBibliothequeService {

	@Autowired Bibliotheque bibliotheque; 
	LivreRepository livreRepo;
	AdherentRepository adherentRepo;
	EmpruntRepository empruntRepo;
	
	@Before
	public void beforeTest() {
		livreRepo = bibliotheque.getLivreRepository();
		adherentRepo = bibliotheque.getAdherentRepository();
		empruntRepo = bibliotheque.getEmpruntRepository();
	}
	
	
	@Test (expected=BusinessException.class)
	public void ajouterLivreTest() {
		try {
			bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
			bibliotheque.ajouterLivre(new Livre("Tintin au Tibet",1952, "Herg�"));
			bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
			bibliotheque.ajouterLivre(new Livre("Tintin au Tibet",1952, "Herg�"));
			bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
			bibliotheque.ajouterLivre(new Livre("Tintin au Tibet",1952, "Herg�"));
			bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
			bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
			bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
			bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
			bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
		}
		catch (BusinessException e) {
			Assert.assertEquals(bibliotheque.getMaxLivreIdentique(), livreRepo.getCountLivreIdentique(new Livre("L'�tranger",1942, "Albert Camus")));
			throw e;
		}
	}
	
	@Test (expected=BusinessException.class)
	public void ajouterAdherent() {
		long nbAdheretbefore = adherentRepo.count();
		try {
			bibliotheque.ajouterAdherent(new Adherent("Durant", "Pascal", "0240563412", "pascal.durant@free.fr"));
			bibliotheque.ajouterAdherent(new Adherent("Martin", "Jean", "0240992345", "jean.martin@laposte.fr"));
			bibliotheque.ajouterAdherent(new Adherent("Martin", "Jean", "0240992345", "jean.martin@laposte.fr"));
		}
		catch (BusinessException e) {
			Assert.assertEquals(nbAdheretbefore+2, adherentRepo.findAll().size());
			throw e;
		}
	}
	
	@Test 
	public void retirerLivre() {
		try {
			Livre l1 = new Livre("Stupeur et tremblements",1999, "Am�lie Nothomb");
			Livre l2 = new Livre("Stupeur et tremblements",1999, "Am�lie Nothomb");
			Livre l3 = new Livre("Stupeur et tremblements",1999, "Am�lie Nothomb");
			
			livreRepo.save(l1);
			livreRepo.save(l2);
			livreRepo.save(l3);
			bibliotheque.retirerLivre(l1.getId());
			bibliotheque.retirerLivre(l2.getId());
			bibliotheque.retirerLivre(l3.getId());
			Assert.assertEquals(0,  livreRepo.getCountLivreIdentique(new Livre("Stupeur et tremblements",1999, "Am�lie Nothomb")));
		
		}
		catch(Exception e) {
			
		}
	}
	@Test 
	public void retirerAdherent() {
		try {
			int nbAdherent = adherentRepo.findAll().size();

			int id1 = bibliotheque.ajouterAdherent(new Adherent("Durant2", "Pascal", "0240563412", "pascal.durant@free.fr"));
			int id2 = bibliotheque.ajouterAdherent(new Adherent("Martin2", "Jean", "0240992345", "jean.martin@laposte.fr"));
			
			Assert.assertEquals(empruntRepo.getEmpruntsEnCoursByAdherent(id1).size(), empruntRepo.getEmpruntsEnCoursByAdherent(id2).size());
			
			bibliotheque.retirerAdherent(id1); 
			bibliotheque.retirerAdherent(id1);
			Assert.assertEquals(nbAdherent,adherentRepo.findAll().size());
		
		}
		catch(Exception e) {
			
		}
	}

	@Test
	public void empruntTest() {
		int idAdherent1 = bibliotheque.ajouterAdherent(new Adherent("Durant2", "Pascal", "0240563412", "pascal.durant@free.fr"));
		int idAdherent2 = bibliotheque.ajouterAdherent(new Adherent("Martin2", "Jean", "0240992345", "jean.martin@laposte.fr"));
		int idLivre1 = bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
		int idLivre2 = bibliotheque.ajouterLivre(new Livre("Tintin au Tibet",1952, "Herg�"));
		int idLivre3 = bibliotheque.ajouterLivre(new Livre("L'�tranger",1942, "Albert Camus"));
		
		Livre l1 = new Livre("Stupeur et tremblements",1999, "Am�lie Nothomb");
		Livre l2 = new Livre("Stupeur et tremblements",1999, "Am�lie Nothomb");
		Livre l3 = new Livre("L'herbe Des Nuits", 2012, "Patrick Modiano");
		livreRepo.save(l1);
		livreRepo.save(l2);
		livreRepo.save(l3);
		livreRepo.save(new Livre("La Fille de papier",  2011,  "Guillaume Musso"));
		
		
		
		//pas deux emprunts sur le m�me livre
		try {
			bibliotheque.emprunterLivre(idLivre1, idAdherent1);
			bibliotheque.emprunterLivre(idLivre1, idAdherent2);
			throw new RuntimeException(); // doit pas arriver l� sinon test KO !
		}
		catch (BusinessException e) {
			Assert.assertEquals("BibliothequeImpl.emprunterLivre", e.getMessage());
		}
		
		//pas plus de 5 emprunts par adherent ...
		try {
			bibliotheque.emprunterLivre(idLivre2, idAdherent1);
			bibliotheque.emprunterLivre(idLivre3, idAdherent1);
			bibliotheque.emprunterLivre(l1.getId(), idAdherent1);
			bibliotheque.emprunterLivre(l2.getId(), idAdherent1);
			bibliotheque.emprunterLivre(l3.getId(), idAdherent1);
			throw new RuntimeException(); // doit pas arriver l� sinon test KO !
		}
		catch (BusinessException e) {
			Assert.assertEquals("BibliothequeImpl.emprunterLivre", e.getMessage());
			
		}	
		
		
		//pas possible de retirer un livre emprunt� ...
		try {
			bibliotheque.retirerLivre(idLivre1);
		}
		catch (BusinessException e) {
			Assert.assertEquals("BibliothequeImpl.retirerLivre", e.getMessage());
			
		}
		
		//pas possible de retirer un adh�rent n'ayant pas restituer tous ses emprunts ...
		try {
			bibliotheque.retirerAdherent(idAdherent1);
		}
		catch (BusinessException e) {
			Assert.assertEquals("BibliothequeImpl.retirerAdherent", e.getMessage());
			
		}
		
		bibliotheque.restituerLivre(idLivre1, idAdherent1);
		bibliotheque.retirerLivre(idLivre1);
		bibliotheque.transfererEmprunt(idAdherent1, idLivre2, idAdherent2);
		bibliotheque.transfererEmprunt(idAdherent1, idLivre3, idAdherent2);
		bibliotheque.transfererEmprunt(idAdherent1, l1.getId(), idAdherent2);
		bibliotheque.transfererEmprunt(idAdherent1, l2.getId(), idAdherent2);
		
		for(Emprunt e : empruntRepo.getAllByLivre(idLivre2))
			System.out.println(e);
		
		for(Emprunt e : empruntRepo.getAllByAdherent(idAdherent1))
			System.out.println(e);
		
		bibliotheque.retirerAdherent(idAdherent1);
	}	
}


