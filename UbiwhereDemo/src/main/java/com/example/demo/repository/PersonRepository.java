package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	@Query("Select p from Person p  left join p.favoriteMusics as m where m.id = ?1")
	List<Person> findAllByMusicId(long musicId);

}
