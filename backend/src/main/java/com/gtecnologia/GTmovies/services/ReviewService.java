package com.gtecnologia.GTmovies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.entities.Review;
import com.gtecnologia.GTmovies.repositories.MovieRepository;
import com.gtecnologia.GTmovies.repositories.ReviewRepository;
import com.gtecnologia.GTmovies.repositories.UserRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;

	@Transactional
	public ReviewDTO postNewReview(ReviewDTO dto) {
		
		Review entity = new Review();
		
		entity.setText(dto.getText());
		entity.setMovie(movieRepository.getOne(dto.getMovieId()));
		entity.setUser(authService.authenticated());
		entity = reviewRepository.save(entity);
		return new ReviewDTO(entity);
	}
}
