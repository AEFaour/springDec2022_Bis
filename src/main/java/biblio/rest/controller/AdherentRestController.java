package biblio.rest.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.fasterxml.jackson.annotation.JsonProperty;

import biblio.entity.Adherent;

@RestController
@RequestMapping("/rest/adherents")
@CrossOrigin(maxAge = 3600)
public class AdherentRestController extends BaseRestController {
	
	
	///rest/adherents/2
	//CRUD 
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAdherent(@PathVariable Integer id ) {
		Optional<Adherent> adherent   =  biblio.getAdherentRepository().findById(id);
		if(!adherent.isPresent())
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(adherent.get(), HttpStatus.OK);
	}
	
	@PostMapping(value="", 
			produces=MediaType.APPLICATION_JSON_VALUE, 
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAdherent(@RequestBody Adherent adherent) {
			int id = biblio.ajouterAdherent(adherent);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(ServletUriComponentsBuilder
					.fromCurrentRequest().path("/{id}")
					.buildAndExpand(id).toUri());
			return  new ResponseEntity<>(adherent, httpHeaders, HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateAdherent(@PathVariable Integer id,   @RequestBody Adherent adherent) {
			if(!biblio.getAdherentRepository().existsById(id))
				return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
			biblio.modifierAdherent(adherent);
			return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdherent(@PathVariable Integer id) {
		biblio.retirerAdherent(id);
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}/short", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ShortAdherent>  getShortAdherent(@PathVariable Integer id ) {
		System.out.println("/adherent/{id}");
		ShortAdherent sa= new ShortAdherent(biblio.getAdherentRepository().findById(id).orElse(null));
		Link link  = WebMvcLinkBuilder.linkTo(AdherentRestController.class).slash(id).withRel("detail");
		sa.add(link);
		sa.add(WebMvcLinkBuilder.linkTo(AdherentRestController.class).slash("hateoas").withRel("list"));
		return new ResponseEntity<>(sa, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Adherent>>  getAdherentAll() {
		System.out.println("/adherent");
		return new ResponseEntity<>(biblio.getAdherentRepository().findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value="/hateoas", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CollectionModel<?>> showAdherentAll() {
		System.out.println("/hateoas");
		List<Adherent> adherents  = biblio.getAdherentRepository().findAll();
		List<Link> links = new ArrayList<Link>();
		for(Adherent a : adherents) 
			links.add(WebMvcLinkBuilder.linkTo(AdherentRestController.class).slash("hateoas").slash(a.getId()).withSelfRel());
		CollectionModel<?> resources = CollectionModel.of(adherents,links);
	    return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@GetMapping(value="/hateoas/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> showAdherent(@PathVariable Integer id) {
		System.out.println("/adherent/"+id);
		Adherent adherent   =  biblio.getAdherentRepository().findById(id).get();
		if(adherent == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	   EntityModel<Adherent> resource = EntityModel.of(adherent, WebMvcLinkBuilder.linkTo(AdherentRestController.class).slash("/hateoas/"+id).withSelfRel());
	   resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdherentRestController.class)
			 	.getAdherent(id))
			 	.withSelfRel());
	   return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	public static class ShortAdherent extends RepresentationModel<ShortAdherent> {
	 
		@JsonProperty
		Integer id;
		@JsonProperty
		String nomPrenom;
	   public ShortAdherent(Adherent adherent) {
		   id = adherent.getId();
		   nomPrenom   = adherent.getNom() + " " + adherent.getPrenom();
	   }
	   public ShortAdherent() { }
	}
}
