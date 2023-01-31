package com.gtecnologia.GTmovies.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTmovies.dtos.MovieDTO;
import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class MovieServiceIT {
	
	@Autowired
	private MovieService movieService;
	
	private long existingId;
	private long nonExistingId;
	private Pageable pageable;
	
	@BeforeEach
	void setUp() throws  Exception{
		
		existingId = 1L;
		nonExistingId = 1000L;
	}
	
	
	@Test
	public void findListMovieShouldReturnListMovieDto() {
		
		List<MovieDTO> list = movieService.findListMovie();
		Assertions.assertNotNull(list);
		Assertions.assertFalse(list.isEmpty());
	}
	
	@Test
	public void findMovieByIdShouldReturnMovieDtoWhenIdExist() {
		
		MovieDTO dto = movieService.findMovieById(existingId);
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(existingId, dto.getId());
	}
	
	@Test
	public void findMovieByIdShouldThrowsResourceNotFoundExcptionWhenIdNonExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			MovieDTO dto = movieService.findMovieById(nonExistingId);
		});
	}
	
	@Test
	public void findPageMovieByGenreShouldReturnPageMovieDto() {
		
		Page<MovieDTO> page = movieService.findPageMovieByGenre(existingId, pageable);
		
		Assertions.assertNotNull(page);
		Assertions.assertFalse(page.isEmpty());
	}
	
	@Test
	public void findPageReviewByMovieShouldReturnPageReviewDto(){
	
		Page<ReviewDTO> page = movieService.findPageReviewByMovie(existingId, pageable);
		Assertions.assertNotNull(page);
		Assertions.assertFalse(page.isEmpty());
	}	
	
	@Test
	public void findListReviewByMovieShouldReturnListReviewDto(){
		
		List<ReviewDTO> list = movieService.findListReviewByMovie(existingId);
		Assertions.assertNotNull(list);
		Assertions.assertFalse(list.isEmpty());
	}
}
