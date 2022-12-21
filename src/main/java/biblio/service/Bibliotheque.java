package biblio.service;

import java.util.List;

import biblio.entity.Adherent;
import biblio.entity.Livre;
import biblio.repository.AdherentRepository;
import biblio.repository.EmpruntRepository;
import biblio.repository.LivreRepository;

public interface Bibliotheque {
	
	public int getMaxLivreIdentique();
	public int getMaxEmpruntAdherent();
	
	public int ajouterLivre(Livre livre);
	public int modifierLivre(Livre livre) ;
	public void retirerLivre(int idLivre) ;
	
	public int ajouterAdherent(Adherent adherent) ;
	public int modifierAdherent(Adherent adherent) ;
	public void retirerAdherent(int idAdherent);
	
	public void emprunterLivre(int idLivre, int idAdherent);
	public void transfererEmprunt(int idAdPrecedent, int idLivre, int idAdSuivant);
	public void restituerLivre(int idLivre, int idAdherent);
	public List<Livre> getLivreAll(); //  { return livreRepo.findAll(); }
	
	public LivreRepository getLivreRepository();
	public AdherentRepository getAdherentRepository();
	public EmpruntRepository getEmpruntRepository();

}


