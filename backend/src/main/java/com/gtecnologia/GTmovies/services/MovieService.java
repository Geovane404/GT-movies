package com.gtecnologia.GTmovies.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTmovies.dtos.MovieDTO;
import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.entities.Genre;
import com.gtecnologia.GTmovies.entities.Movie;
import com.gtecnologia.GTmovies.entities.Review;
import com.gtecnologia.GTmovies.repositories.GenreRepository;
import com.gtecnologia.GTmovies.repositories.MovieRepository;
import com.gtecnologia.GTmovies.repositories.ReviewRepository;
import com.gtecnologia.GTmovies.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional(readOnly = true)
	public List<MovieDTO> findListMovie() {

		List<Movie> list = movieRepository.findAll();
		return list.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public MovieDTO findMovieById(Long id) {
		
		Optional<Movie> obj = movieRepository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new MovieDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findPageMovieByGenre(Long genreId, Pageable pageable) {

		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> page = movieRepository.findPageMovieByGenre(genre, pageable);
		return page.map(x -> new MovieDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Page<ReviewDTO> findPageReviewByMovie(Long idMovie, Pageable pageable) {
		
		Movie movie = movieRepository.getOne(idMovie);
		Page<Review> page = reviewRepository.findPageReviewByMovie(movie, pageable);
		return page.map(x -> new ReviewDTO(x));
	}
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findListReviewByMovie(Long idMovie) {
		
		List<Review> list = reviewRepository.findListReviewByMovie(idMovie);
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
}
