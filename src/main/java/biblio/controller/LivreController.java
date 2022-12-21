package biblio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import biblio.entity.Livre;
import biblio.service.Bibliotheque;
import biblio.service.BusinessException;


@Controller
@RequestMapping("/jsp/livres")
public class LivreController {
	@Autowired
	Bibliotheque biblio;
	
	@RequestMapping("/lister")
	public String lister(Model model) {
		System.out.println("**** lister ****");
		model.addAttribute("livres", biblio.getLivreRepository().findAll());
		return "livres";	
	}
	
	@RequestMapping("/editer")
	public String editer(@RequestParam Integer id,  Model model) {
		model.addAttribute("livre", biblio.getLivreRepository().findById(id).orElse(null));
		return "livre";	
	}
	
	@RequestMapping(value="/action", method=RequestMethod.POST)
	public String action(@RequestParam String action, Livre livre, Errors errors) {
	
		System.out.println("action");
		if(!errors.hasErrors())
			switch (action) {
			case "create":
				biblio.ajouterLivre(livre);
				break;
			case "update":
				biblio.getLivreRepository().save(livre);
				break;
			case "delete":
				biblio.retirerLivre(livre.getId());
				break;
			}
		System.out.println("redirect:/spring/livres/lister");
		return "redirect:lister";	
	}	
	
	@ExceptionHandler(BusinessException.class)
	public String traiterErreur(BusinessException e, Model model) {
		model.addAttribute("erreur", e.getMessage());
		return "erreur";
	}

}
