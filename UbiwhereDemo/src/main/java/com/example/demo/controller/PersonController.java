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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class PersonController {
	
    private final Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;
		
	/**
	 * Returns user list
	 *    
	 * @return      		user list
	 */
	@RequestMapping(method=RequestMethod.GET)
	public List<Person> getUsers() {
		log.debug("[PersonController] getUsers");
		
		return personService.findAll();
	}
	
	/**
	 * Returns a specific user info
	 * 
	 * @PathVariable  id		user id   
	 * @return      			user
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Person getUserById(@PathVariable("id") Long id) {
		log.debug("[PersonController] getUserById");
		
		return personService.findById(id);
	}
	
	/**
	 * Add new user
	 * 
	 * @RequestBody  p		json user   
	 * @return      			user id
	 */
	@RequestMapping(method=RequestMethod.POST)
	public long addUser(@RequestBody Person p) throws JsonProcessingException {
		log.debug("[PersonController] addUser");
	
		if (personService.save(p)!=null)
			return p.getId();
		
		return -1;
	}
	
	/**
	 * Update user
	 * 
	 * @PathVariable  id		user id 
	 * @RequestBody   p		json user   
	 * @return      			user id
	 */	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public long editUserById(@PathVariable("id") Long id, @RequestBody Person p) throws JsonProcessingException {
		log.debug("[PersonController] editUserById");
	
		if (personService.update(p)!=null)
			return p.getId();		
		return -1;
	}
	
	/**
	 * delete user
	 * 
	 * @PathVariable  id		user id
	 * @return      			"done" ( if 200 ok )
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUserById(@PathVariable("id") Long id) {
		log.debug("[PersonController] deleteUserById");
		
		personService.deleteById(id);			
		return "done";
	}
	
}


