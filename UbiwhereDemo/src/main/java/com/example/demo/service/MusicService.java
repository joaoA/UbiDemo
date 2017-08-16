package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Music;
import com.example.demo.repository.MusicRepository;

@Service
public class MusicService {

	
	@Autowired
	private MusicRepository musicRepo;

	public List<Music> findAll(){
	    List<Music> rs = new ArrayList<>();
	    musicRepo.findAll().forEach(rs::add);
		return rs;
	}
	
	public List<Music> findAllByUserId(long id){ 
		return musicRepo.findAllByUserId(id);
	}
	
	public Music findById(long id){
		return musicRepo.findOne(id);
	}
	
	public Music save(Music p){
		return musicRepo.save(p);
	}
	
	public Music update(Music p){
		if ( p == null || !musicRepo.exists(p.getId()))
			return null;
			
		return musicRepo.save(p);
	}
	
	public void deleteById(long id){
		musicRepo.delete(id);
	}
	
	
}
