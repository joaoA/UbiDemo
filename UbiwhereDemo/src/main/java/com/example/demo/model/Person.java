package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private String name;
	
	private String email;
	
	
	@ManyToMany(cascade= CascadeType.ALL)
	private List<Music> favoriteMusics;

	public Person() {}
	
	public Person(String name, String email, List<Music> musics) {
		this.name = name;
		this.email = email;
		
		if (musics==null) {
			this.favoriteMusics = new ArrayList<Music>();
		}else {					
			this.favoriteMusics = musics;
		}

	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Music> getFavoriteMusics() {
		return favoriteMusics;
	}

	public void setFavoriteMusics(List<Music> favoriteMusics) {
		this.favoriteMusics = favoriteMusics;
	}
	
	
}
