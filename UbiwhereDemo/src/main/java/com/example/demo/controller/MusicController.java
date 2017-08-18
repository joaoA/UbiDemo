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
import com.example.demo.model.Person;
import com.example.demo.service.MusicService;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/api/musics")
public class MusicController {
	
    private final Logger log = LoggerFactory.getLogger(MusicController.class);

	@Autowired
	private MusicService musicService;
	
	@Autowired
	private PersonService personService;
	
	/**
	 * Returns music list ( all by default or filtered by user )
	 * 
	 * @param  userid	id of user   
	 * @return      		music list
	 */
	@RequestMapping(method=RequestMethod.GET)
	public List<Music> getMusics(@RequestParam(value = "userid", required=false) Long id) {
		log.debug("[MusicController] getMusics");
			
		if(id == null) 
			return musicService.findAll();		
		else 
			return musicService.findAllByUserId(id);

	}
	
	/**
	 * Returns a specific music info
	 * 
	 * @PathVariable  id		music id   
	 * @return      			music
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Music getMusicById(@PathVariable("id") Long id) {
		log.debug("[MusicController] getMusicById");
		
		return musicService.findById(id);
	}
		
	
	/**
	 * Add new music
	 * 
	 * @RequestBody  m		json music   
	 * @return      			music id
	 */
	@RequestMapping(method=RequestMethod.POST)
	public long addMusic(@RequestBody Music m) {
		log.debug("[MusicController] addMusic");
		if (musicService.save(m)!=null)
			return m.getId();
		
		return -1;
	}
	
	/**
	 * Update music
	 * 
	 * @PathVariable  id		music id 
	 * @RequestBody   m		json music   
	 * @return      			music id
	 */	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public long editMusicById(@PathVariable("id") Long id, @RequestBody Music m) {
		log.debug("[MusicController] editMusicById");
		
		if (musicService.update(m)!=null)
			return m.getId();		
		return -1;
	}
	
	/**
	 * delete music
	 * 
	 * @PathVariable  id		music id
	 * @return      			"done" ( if 200 ok )
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteMusicById(@PathVariable("id") Long id) {
		log.debug("[MusicController] deleteMusicById");
		
		Music m =  musicService.findById(id);
		
		if (m!= null) {
			personService.findAllByPersonId(m.getId()).forEach(user -> {
				Person aux = personService.findById(user.getId());
				List<Music> fav = aux.getFavoriteMusics();
				fav.remove(m);
				aux.setFavoriteMusics(fav);
				personService.update(aux);
			});
		}
		musicService.deleteById(id);			
		return "done";
	}
	

}
