package com.gtecnologia.GTmovies.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTmovies.dtos.GenreDTO;
import com.gtecnologia.GTmovies.entities.Genre;
import com.gtecnologia.GTmovies.repositories.GenreRepository;

@Service
public class GenreServices {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Transactional(readOnly = true)
	public List<GenreDTO> allGenres(){
		
		List<Genre> list = genreRepository.findAll();
		return list.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
	}
}
