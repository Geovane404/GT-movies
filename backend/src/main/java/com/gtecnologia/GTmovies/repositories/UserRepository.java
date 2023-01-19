package com.gtecnologia.GTmovies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtecnologia.GTmovies.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
