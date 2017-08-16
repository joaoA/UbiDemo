package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Artist;;

public interface ArtistRepository extends CrudRepository<Artist, Long> {}
