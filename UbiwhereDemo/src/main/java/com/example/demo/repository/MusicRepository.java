package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Music;;

public interface MusicRepository extends CrudRepository<Music, Long> {
	
	@Query("Select m from Music m  left join m.users as u where u.id = ?1")
	List<Music> findAllByUserId(long userId);
}


