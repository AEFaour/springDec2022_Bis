package biblio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import biblio.entity.Adherent;
import biblio.service.Bibliotheque;
import biblio.service.BusinessException;


@Controller
@RequestMapping("/jsp/adherents")
public class AdherentController {
	
	@Autowired
	Bibliotheque biblio;
	
	@RequestMapping("/lister")
	public String getAdherentAll(Model model) {
		model.addAttribute("adherents", biblio.getAdherentRepository().findAll());
		return "adherents";
	}
	
	@RequestMapping("/editer")
	public String getAdherent(@RequestParam int id, Model model) {
		model.addAttribute("adherent", biblio.getAdherentRepository().findById(id).orElse(null));
		return "adherent";
	}
	
	@RequestMapping("/action")
	public String action(@RequestParam String action, Adherent adherent) {
		switch (action) {
			case "update":
				biblio.getAdherentRepository().save(adherent);
				break;
			case "delete":
				biblio.retirerAdherent(adherent.getId());
				break;
			case "create":
				biblio.ajouterAdherent(adherent);
				break;
		}
		
		return "redirect:lister";
	}
	
	@ExceptionHandler(BusinessException.class)
	public String traiterErreur(BusinessException e, Model model) {
		model.addAttribute("erreur", e.getMessage());
		return "erreur";
	}

}
