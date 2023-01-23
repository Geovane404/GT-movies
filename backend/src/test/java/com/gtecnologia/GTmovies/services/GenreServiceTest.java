package com.gtecnologia.GTmovies.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gtecnologia.GTmovies.dtos.GenreDTO;
import com.gtecnologia.GTmovies.entities.Genre;
import com.gtecnologia.GTmovies.repositories.GenreRepository;


@ExtendWith(SpringExtension.class)
public class GenreServiceTest {
	
	@InjectMocks
	private GenreServices genreServices;
	
	@Mock
	private GenreRepository genreRepository;
	
	private List<Genre> list;
	
	@BeforeEach
	void setUp()throws Exception {
		
		list = new ArrayList<>();
		list.add(new Genre(1L, "Comédia"));
		
		Mockito.when(genreRepository.findAll()).thenReturn(list);
	}
	
	
	@Test
	public void allGenresShoudListGenreDTO() {
		
		List<GenreDTO> list = genreServices.allGenres();
		
		Assertions.assertFalse(list.isEmpty());
		Assertions.assertNotNull(list);
		Mockito.verify(genreRepository).findAll();
	}
}
