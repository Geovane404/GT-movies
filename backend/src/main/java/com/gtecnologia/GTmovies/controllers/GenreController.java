package com.gtecnologia.GTmovies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtecnologia.GTmovies.dtos.GenreDTO;
import com.gtecnologia.GTmovies.services.GenreServices;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {

	@Autowired
	private GenreServices genreServices;
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> allGenres(){
		
		List<GenreDTO> list = genreServices.allGenres();
		return ResponseEntity.ok().body(list);
	}
}
