package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private String name;
	
	private String downloadUrl;
	
	private String listenUrl;
	
	@ManyToOne
	@JoinColumn(name = "albumId")
	private Album album;
	
	@ManyToMany
	private List<Person> users;

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

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getListenUrl() {
		return listenUrl;
	}

	public void setListenUrl(String listenUrl) {
		this.listenUrl = listenUrl;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<Person> getUsers() {
		return users;
	}

	public void setUsers(List<Person> users) {
		this.users = users;
	}
	
	
}
