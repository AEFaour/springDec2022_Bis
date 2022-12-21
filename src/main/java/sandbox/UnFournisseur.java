package sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("unFournisseur")
public class UnFournisseur implements Fournisseur { //POJO --> Plain Old Java Object --> Java Bean
	
	
	public UnFournisseur() {
	System.out.println("UnFournisseur()");
	}
	
	@Value("${fournisseur.nomm:Inconnu}")
	String nom;

	@Override
	public void rendreUnService() {
		System.out.println("moi : " + nom 
				+ " je rends un service à mon client");
		
	}

	public void setNom(String nom) {
		this.nom=nom;
		
	}
}
