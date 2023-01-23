package com.gtecnologia.GTmovies.repositories;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gtecnologia.GTmovies.entities.Movie;
import com.gtecnologia.GTmovies.entities.Review;
import com.gtecnologia.GTmovies.entities.User;

@DataJpaTest
public class ReviewRepositoryTest {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired 
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository usereRepository;
	
	private long existingId;
	private Review review;
	private Movie movie;
	private User user;
	private Pageable pageable;
	private long countTotalReviews;
	
	@BeforeEach
	void setUp() throws Exception{
		
		existingId = 1L;
		review = new Review();
		movie = movieRepository.getOne(existingId);
		user = usereRepository.getOne(existingId);
		countTotalReviews = reviewRepository.count();
	}
	
	@Test
	public void saveShouldPersistWithAutoIncrementAndReturnReviewWhenIdIsNull() {
		
		review.setId(null);
		review.setText("Filme top!");
		review.setMovie(movie);
		review.setUser(user);
		review = reviewRepository.save(review);
		
		Assertions.assertEquals(countTotalReviews + 1, review.getId());
		Assertions.assertNotNull(review);
		Assertions.assertEquals(movie.getId(), review.getMovie().getId());
		Assertions.assertEquals(user.getId(), review.getUser().getId());	
	}
	
	@Test
	public void findPageReviewByMovieShouldReturnPageReviews() {
		
		Page<Review> page = reviewRepository.findPageReviewByMovie(movie, pageable);
		
		Assertions.assertFalse(page.isEmpty());
		Assertions.assertNotNull(page);
	}
	
	@Test
	public void findListReviewByMovieShouldReturnListReviews() {
		
		List<Review> list = reviewRepository.findListReviewByMovie(existingId);
		
		Assertions.assertFalse(list.isEmpty());
		Assertions.assertNotNull(list);
	}
}
