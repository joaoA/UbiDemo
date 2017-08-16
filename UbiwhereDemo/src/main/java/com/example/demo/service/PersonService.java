package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepo;

	public List<Person> findAll(){
	    List<Person> rs = new ArrayList<>();
	    personRepo.findAll().forEach(rs::add);
		return rs;
	}
	
	public Person findById(long id){
		return personRepo.findOne(id);
	}
	
	public Person save(Person p){
		return personRepo.save(p);
	}
	
	public Person update(Person p){
		if ( p == null || !personRepo.exists(p.getId()))
			return null;
			
		return personRepo.save(p);
	}
	
	public void deleteById(long id){
		personRepo.delete(id);
	}
}
