package com.gtecnologia.GTmovies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtecnologia.GTmovies.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
