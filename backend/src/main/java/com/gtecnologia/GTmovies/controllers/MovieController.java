package com.gtecnologia.GTmovies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtecnologia.GTmovies.dtos.MovieDTO;
import com.gtecnologia.GTmovies.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> findListMovie(){
	
		List<MovieDTO> list = movieService.findListMovie();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findMovieById(@PathVariable Long id) {
		
		MovieDTO dto = movieService.findMovieById(id);
		return ResponseEntity.ok().body(dto);
	}
}
