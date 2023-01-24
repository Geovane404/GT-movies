package com.gtecnologia.GTmovies.services;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.entities.Movie;
import com.gtecnologia.GTmovies.entities.Review;
import com.gtecnologia.GTmovies.entities.User;
import com.gtecnologia.GTmovies.factories.Factory;
import com.gtecnologia.GTmovies.repositories.MovieRepository;
import com.gtecnologia.GTmovies.repositories.ReviewRepository;

@ExtendWith(SpringExtension.class)
public class ReviewServiceTest {
	
	@InjectMocks
	private ReviewService reviewService;
	
	@Mock
	private ReviewRepository reviewRepository;
	
	@Mock
	private MovieRepository movieRepository;
	
	@Mock
	private AuthService authService;
	
	private ReviewDTO reviewDTO;
	private Review review;
	private Movie movie;
	private User user;
	
	@BeforeEach
	void setUp()throws Exception{
		
		reviewDTO = Factory.createReviewDto();
		review =Factory.createReview();
		movie = Factory.createMovie();
		user = Factory.createUser();
		
		Mockito.when(reviewRepository.save(ArgumentMatchers.any())).thenReturn(review);
		when(movieRepository.getOne(ArgumentMatchers.any())).thenReturn(movie);
		when(authService.authenticated()).thenReturn(user);
	}
	
	@Test
	public void postNewReviewShouldReturnReviewDto() {
		
		ReviewDTO dto = reviewService.postNewReview(reviewDTO);
		
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(movie.getId(), dto.getMovieId());
		Assertions.assertEquals(user.getId(), dto.getUser().getId());
	}
}
