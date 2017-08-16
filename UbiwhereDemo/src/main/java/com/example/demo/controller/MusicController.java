package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public List<Music> getMusics() {
		log.debug("[MusicController] getUsers");
		
		return musicService.findAll();
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public List<Music> getMusicsByUser(@PathVariable("id") long id) {
		log.debug("[MusicController] getUsers");
		
		return musicService.findAllByUserId(id);
	}
	
}
