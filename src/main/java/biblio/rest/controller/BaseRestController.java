package biblio.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;

import biblio.service.Bibliotheque;
import biblio.service.BusinessException;


public abstract class BaseRestController {
	
	@Autowired
	Bibliotheque biblio;
	
	
	protected ResponseEntity<?> validationNotOk(Errors errors) {
		// get all errors 
	    List<String> result = errors.getAllErrors()
			.stream()
			.map(x -> x.getDefaultMessage())
			.collect(Collectors.toList());
			
	    return ResponseEntity.badRequest().body(result);
	}
	
	@ExceptionHandler(Exception.class)
	
	protected ResponseEntity<?> responseError(Exception e) {
		if(e instanceof BusinessException )
			return  new ResponseEntity<>(e.getClass().getSimpleName() + " : " + e.getMessage(), HttpStatus.NOT_ACCEPTABLE) ;
		else
			return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
}
