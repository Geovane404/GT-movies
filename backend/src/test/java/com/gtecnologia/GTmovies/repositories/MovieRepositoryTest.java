package com.gtecnologia.GTmovies.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gtecnologia.GTmovies.entities.Genre;
import com.gtecnologia.GTmovies.entities.Movie;

@DataJpaTest
public class MovieRepositoryTest {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	private Long existingId;
	private Long nonExistingId;
	
	private Genre genreExist;
	private Genre genreNonExist;
	private Pageable pageable;
	
	private Long countTotalMovies;
	
	@BeforeEach
	void setUp()throws Exception{
		
		existingId = 1L;
		nonExistingId = 1000L;
		
		genreExist = genreRepository.getOne(existingId);
		genreNonExist = genreRepository.getOne(nonExistingId);
		countTotalMovies = movieRepository.count();
	}
	
	
	@Test
	public void findAllShouldReturnListMovie() {
		
		List<Movie> list = movieRepository.findAll();
		
		Assertions.assertFalse(list.isEmpty());
	}
	
	@Test
	public void findByIdShouldReturnOptionalNotNullWhenIdExist() {
		
		Optional<Movie> obj = movieRepository.findById(existingId);
		
		Assertions.assertTrue(obj.isPresent());
		Assertions.assertFalse(obj.isEmpty());
		Assertions.assertNotNull(obj);
		Assertions.assertNotEquals(obj, this.existingId);
		Assertions.assertEquals(obj.get().getId(), existingId);
	}
	
	@Test
	public void findByIdShouldReturnOptionalEmptyWhenIdNoExist() {
		
		Optional<Movie> obj = movieRepository.findById(nonExistingId);
		
		Assertions.assertTrue(obj.isEmpty());
		Assertions.assertFalse(obj.isPresent());
	}
	
	@Test
	public void findPageMovieByGenreShouldReturnPagePerGenreWhenGenreExist() {
		
		Page<Movie> page = movieRepository.findPageMovieByGenre(genreExist, pageable);
		
		Assertions.assertFalse(page.isEmpty());
		Assertions.assertNotEquals(page.getTotalElements(), countTotalMovies);
	}
	
	@Test
	public void findPageMovieByGenreShouldReturnPageEmptyWhenGenreNoExist() {
		
		Page<Movie> page = movieRepository.findPageMovieByGenre(genreNonExist, pageable);
		
		Assertions.assertTrue(page.isEmpty());
	}
}
