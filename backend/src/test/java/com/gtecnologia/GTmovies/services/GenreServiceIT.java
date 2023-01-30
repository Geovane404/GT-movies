package com.gtecnologia.GTmovies.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTmovies.dtos.GenreDTO;

@Transactional
@SpringBootTest
public class GenreServiceIT {
	
	@Autowired
	private GenreServices genreServices;
	
	@Test
	public void allGenresShouldReturnListGenre() {
		
		List<GenreDTO> list = genreServices.allGenres();
		
		Assertions.assertFalse(list.isEmpty());
		Assertions.assertNotNull(list);
	}
}
