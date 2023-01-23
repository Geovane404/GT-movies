package com.gtecnologia.GTmovies.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gtecnologia.GTmovies.dtos.MovieDTO;
import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.entities.Movie;
import com.gtecnologia.GTmovies.entities.Review;
import com.gtecnologia.GTmovies.factories.Factory;
import com.gtecnologia.GTmovies.repositories.GenreRepository;
import com.gtecnologia.GTmovies.repositories.MovieRepository;
import com.gtecnologia.GTmovies.repositories.ReviewRepository;
import com.gtecnologia.GTmovies.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {
	
	@InjectMocks
	private MovieService movieService;
	
	@Mock
	private MovieRepository movieRepository;
	
	@Mock
	private GenreRepository genreRepository;
	
	@Mock
	private ReviewRepository reviewRepository;
	
	private Movie movie;
	private Review review;
	private List<Movie> list;
	private List<Review> listReview;
	
	private long existingId;
	private long nonExistingId;
	
	private Pageable pageable;
	private PageImpl<Movie> page;
	private PageImpl<Review> pageReview;

	@BeforeEach
	void setUp() throws Exception{
		
		movie = Factory.createMovie();
		list = new ArrayList<>();
		list.add(movie);
		
		page = new PageImpl<>(List.of(movie));
		
		review = Factory.createReview();
		listReview = new ArrayList<>();
		listReview.add(review);
		
		pageReview = new PageImpl<>(List.of(review));
		
		existingId = 1L;
		nonExistingId  = 2L;
	
		Mockito.when(movieRepository.findAll()).thenReturn(list);
		when(movieRepository.findById(existingId)).thenReturn(Optional.of(movie));
		when(movieRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		when(movieRepository.findPageMovieByGenre(movie.getGenre(), pageable)).thenReturn(page);
		when(genreRepository.getOne(existingId)).thenReturn(movie.getGenre());
		
		when(reviewRepository.findPageReviewByMovie(movie, pageable)).thenReturn(pageReview);
		when(movieRepository.getOne(movie.getId())).thenReturn(movie);
		
		when(reviewRepository.findListReviewByMovie(movie.getId())).thenReturn(listReview);
	}
	
	@Test
	public void findListMovieShouldReturnListMovieDto() {
		
		List<MovieDTO> list = movieService.findListMovie();
		
		Assertions.assertFalse(list.isEmpty());
		Assertions.assertNotNull(list);
		Mockito.verify(movieRepository).findAll();
	}
	
	@Test
	public void findMovieByIdShouldReturnMovieDtoWhenExistingId() {
		
		MovieDTO dto = movieService.findMovieById(existingId);
		
		Assertions.assertTrue(dto.getId().equals(existingId));
		Assertions.assertEquals(dto.getId(), existingId);
		Assertions.assertEquals(dto.getGenre().getId(), movie.getGenre().getId());
		verify(movieRepository).findById(existingId);
	}
	
	@Test
	public void findMovieByIdShouldThrowResourceNotFounExcptionWhenNoExistId() {
		
		 Assertions.assertThrows(ResourceNotFoundException.class , () -> {
			 MovieDTO dto = movieService.findMovieById(nonExistingId);
		 });
		 verify(movieRepository).findById(nonExistingId);
	}
	
	@Test
	public void findPageMovieByGenreShouldReturnPageMovieDto() {
		
		Page<MovieDTO> result = movieService.findPageMovieByGenre(existingId, pageable);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertNotEquals(page, result);
		Assertions.assertNotNull(result);
		verify(movieRepository).findPageMovieByGenre(movie.getGenre(), pageable);
		verify(genreRepository).getOne(existingId);
	}
	
	@Test
	public void findPageReviewByMovieShouldReturnPageReviewDto() {
		
		Page<ReviewDTO> result = movieService.findPageReviewByMovie(movie.getId(), pageable);
		
		Assertions.assertFalse(result.isEmpty());
		verify(reviewRepository).findPageReviewByMovie(movie, pageable);
		verify(movieRepository).getOne(movie.getId());
	}
	
	@Test
	public void findListReviewByMovieShouldReturnListReviewDto() {
		
		List<ReviewDTO> result = movieService.findListReviewByMovie(movie.getId());
		
		Assertions.assertFalse(result.isEmpty());
		verify(reviewRepository).findListReviewByMovie(movie.getId());
	}
}
