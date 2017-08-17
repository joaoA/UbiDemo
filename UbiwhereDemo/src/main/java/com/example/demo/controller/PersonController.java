package com.example.demo.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class PersonController {
	
    private final Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;
		
	@RequestMapping(method=RequestMethod.GET)
	public List<Person> getUsers() {
		log.debug("[PersonController] getUsers");
		
		return personService.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Person getUserById(@PathVariable("id") Long id) {
		log.debug("[PersonController] getUserById");
		
		return personService.findById(id);
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public long addUser(@RequestBody Person p) throws JsonProcessingException {
		log.debug("[PersonController] addUser");
		
		log.error("******************************");
		log.error(new ObjectMapper().writeValueAsString(p));
		log.error("******************************");
		
		
		if (personService.save(p)!=null)
			return p.getId();
		
		return -1;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public long editUserById(@PathVariable("id") Long id, @RequestBody Person p) throws JsonProcessingException {
		log.debug("[PersonController] editUserById");
		
		log.error("******************************");
		log.error(new ObjectMapper().writeValueAsString(p));
		log.error("******************************");
		
		if (personService.update(p)!=null)
			return p.getId();		
		return -1;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUserById(@PathVariable("id") Long id) {
		log.debug("[PersonController] deleteUserById");
		
		personService.deleteById(id);			
		return "done";
	}
	
}


