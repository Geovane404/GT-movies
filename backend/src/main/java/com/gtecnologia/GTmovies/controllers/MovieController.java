package com.gtecnologia.GTmovies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtecnologia.GTmovies.dtos.MovieDTO;
import com.gtecnologia.GTmovies.dtos.ReviewDTO;
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
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<MovieDTO>> findPageMovieByGenre(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			Pageable pageable){
		
		Page<MovieDTO> page = movieService.findPageMovieByGenre(genreId, pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping(value = "/{idMovie}/reviews")
	public ResponseEntity<Page<ReviewDTO>> findPageReviewByMovie(@PathVariable Long idMovie, Pageable pageable){
		
		Page<ReviewDTO> page = movieService.findPageReviewByMovie(idMovie, pageable);
		return ResponseEntity.ok().body(page);
	}
}
