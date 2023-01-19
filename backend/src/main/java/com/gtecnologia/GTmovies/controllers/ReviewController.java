package com.gtecnologia.GTmovies.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.services.ReviewService;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ReviewDTO> postNewReview(@RequestBody ReviewDTO dto){
		
		dto = reviewService.postNewReview(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
