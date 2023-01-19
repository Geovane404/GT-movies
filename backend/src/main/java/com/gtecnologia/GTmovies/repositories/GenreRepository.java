package com.gtecnologia.GTmovies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtecnologia.GTmovies.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
