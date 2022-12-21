package sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnClient {  //POJO --> Java Bean

	@Autowired
	Fournisseur fournisseur;
	
	public void faireQQChose() {
		fournisseur.rendreUnService();	
	}
	
	public UnClient() {} //constructeur par défaut 
	
	
	public UnClient(Fournisseur fournisseur) {
		super();
		System.out.println("UnClient()");
		this.fournisseur = fournisseur;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	

}
