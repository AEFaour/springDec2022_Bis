package biblio.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import biblio.entity.Livre;

@RestController
@RequestMapping(value="/rest/livres")
@CrossOrigin(maxAge = 3600)
public class LivreRestController extends BaseRestController {
		
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Livre>> getLivreAll() {
		return new ResponseEntity<List<Livre>>(
				biblio.getLivreRepository().findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> getLivre( @PathVariable Integer id) {
		Livre l  = biblio.getLivreRepository().findById(id).orElse(null);
		return l != null ?
				new ResponseEntity<Livre>(l, HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			     produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> createLivre(@RequestBody @Valid Livre livre, Errors errors) { 
		//If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {
			return validationNotOk(errors);
		}
		int id = biblio.ajouterLivre(livre);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri());
		return new ResponseEntity<>(livre, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			                     produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> updateLivre(@PathVariable Integer id, @RequestBody @Valid Livre livre, Errors errors) {
		//If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {
			return validationNotOk(errors);
		}
		biblio.modifierLivre(livre);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLivre(@PathVariable Integer id) {
			if(!biblio.getLivreRepository().existsById(id))
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			biblio.retirerLivre(id);
			return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
