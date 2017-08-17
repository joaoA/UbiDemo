package com.example.demo.utils;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Music;
import com.example.demo.service.MusicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class Utils implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private MusicService musicService;
	
	
	 @Override
	  public void onApplicationEvent(final ApplicationReadyEvent event) {
		 System.out.println("****** STARTOU ******");
		 if (musicService.count()<=0)
			 getMusics();
	
	    return;
	  }
	 
	
	
	public void getMusics() 
	{
		 
		
		
		JsonNode rootNode =  null; 
		
	    final String url = "http://freemusicarchive.org/recent.json ";

//	    RestTemplate restTemplate = new RestTemplate();
//	    String result = restTemplate.getForObject(url, String.class);
//	    
	    
	    RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
        ResponseEntity<String> res = rt.exchange(url, HttpMethod.GET, entity, String.class);
        
	    
	    try {
			rootNode = new ObjectMapper().readTree(res.getBody());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("************");
	    rootNode.path("aTracks").forEach(item -> { 
	    		String name 			= item.path("track_title").textValue();
	    		String downloadUrl 	= item.path("track_file_url").textValue();
	    		String listenUrl 	= item.path("track_listen_url").textValue();
	    		musicService.save(new Music(name, downloadUrl, listenUrl));
	    });
	    System.out.println(rootNode.path("aTracks"));
	    System.out.println("************");
	    
	}
	
}
