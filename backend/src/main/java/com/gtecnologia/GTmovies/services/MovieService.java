package com.gtecnologia.GTmovies.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTmovies.dtos.MovieDTO;
import com.gtecnologia.GTmovies.entities.Movie;
import com.gtecnologia.GTmovies.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Transactional(readOnly = true)
	public List<MovieDTO> findListMovie() {

		List<Movie> list = movieRepository.findAll();
		return list.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
	}
}
