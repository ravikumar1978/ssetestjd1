package com.jd.ssetest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jd.ssetest.domain.Person;
import com.jd.ssetest.http.header.HeaderGenerator;
import com.jd.ssetest.service.IService.IPersonService;


@RestController
public class PersonController {
	@Autowired
	private HeaderGenerator headerGenerator;
	
	@Autowired
	private IPersonService personService;

	
	 @PostMapping(value = "/persons")
	    private ResponseEntity<Person> addPerson(@RequestBody Person Person, HttpServletRequest request){
	    	if(Person != null) {
	    		try {
	    			personService.addPerson(Person);
	    	        return new ResponseEntity<Person>(
	    	        		Person,
	    	        		headerGenerator.getHeadersForSuccessPostMethod(request, Person.getId()),
	    	        		HttpStatus.CREATED);
	    		}catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<Person>(
							headerGenerator.getHeadersForError(),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
	    	}
	    	return new ResponseEntity<Person>(
	    			headerGenerator.getHeadersForError(),
	    			HttpStatus.BAD_REQUEST);       
	    }
	    
	    @DeleteMapping(value = "/Persons/{id}")
	    private ResponseEntity<Void> deletePerson(@PathVariable("id") Long id){
	    	Person Person = personService.getPersonById(id);
	    	if(Person != null) {
	    		try {
	    			personService.deletePerson(id);
	    	        return new ResponseEntity<Void>(
	    	        		headerGenerator.getHeadersForSuccessGetMethod(),
	    	        		HttpStatus.OK);
	    		}catch (Exception e) {
					e.printStackTrace();
	    	        return new ResponseEntity<Void>(
	    	        		headerGenerator.getHeadersForError(),
	    	        		HttpStatus.INTERNAL_SERVER_ERROR);
				}
	    	}
	    	return new ResponseEntity<Void>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);      
	    }
	    
	    @GetMapping (value = "/persons")
	    public ResponseEntity<List<Person>> getAllPersons(){
	        List<Person> Persons =  personService.getAllPersons();
	        if(!Persons.isEmpty()) {
	        	return new ResponseEntity<List<Person>>(
	        			Persons,
	        			headerGenerator.getHeadersForSuccessGetMethod(),
	        			HttpStatus.OK);
	        }
	        return new ResponseEntity<List<Person>>(
	        		headerGenerator.getHeadersForError(),
	        		HttpStatus.NOT_FOUND);       
	    }
	    
	    @GetMapping (value = "/personcount")
	    public ResponseEntity<Long> getPersonCount(){
	    	Long count =  personService.getPersonCount();
	        if(!(count==null)) {
	        	return new ResponseEntity<Long>(
	        			count,
	        			headerGenerator.getHeadersForSuccessGetMethod(),
	        			HttpStatus.OK);
	        }
	        return new ResponseEntity<Long>(
	        		headerGenerator.getHeadersForError(),
	        		HttpStatus.NOT_FOUND);       
	    }
			
}
