package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Music;
import com.example.demo.service.MusicService;

@RestController
@RequestMapping("/api/musics")
public class MusicController {
	
    private final Logger log = LoggerFactory.getLogger(MusicController.class);

	@Autowired
	private MusicService musicService;
    
	@RequestMapping(method=RequestMethod.GET)
	public List<Music> getMusics(@RequestParam(value = "userid", required=false) Long id) {
		log.debug("[MusicController] getUsers");
			
		if(id == null) 
			return musicService.findAll();		
		else 
			return musicService.findAllByUserId(id);

	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Music getUserById(@PathVariable("id") Long id) {
		log.debug("[MusicController] getUserById");
		
		return musicService.findById(id);
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public long addUser(@RequestBody Music p) {
		log.debug("[MusicController] addUser");
		if (musicService.save(p)!=null)
			return p.getId();
		
		return -1;
	}
	
		
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public long editUserById(@PathVariable("id") Long id, @RequestBody Music p) {
		log.debug("[MusicController] editUserById");
		
		if (musicService.update(p)!=null)
			return p.getId();		
		return -1;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUserById(@PathVariable("id") Long id) {
		log.debug("[MusicController] deleteUserById");
		
		musicService.deleteById(id);			
		return "done";
	}
	

}
