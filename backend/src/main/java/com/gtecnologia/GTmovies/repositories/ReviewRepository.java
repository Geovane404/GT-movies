package com.gtecnologia.GTmovies.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gtecnologia.GTmovies.entities.Movie;
import com.gtecnologia.GTmovies.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	@Query("SELECT obj FROM Review obj "
			+ "WHERE obj.movie = :movie")
	Page<Review> findPageReviewByMovie(Movie movie, Pageable pageable);

}
