package com.gtecnologia.GTmovies.repositories;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gtecnologia.GTmovies.entities.Genre;

@DataJpaTest
public class GenreRepositoryTest {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Test
	public void findAllShouldReturnListGenre() {
		
		List<Genre> list = genreRepository.findAll();
		
		Assertions.assertFalse(list.isEmpty());
	}
}
