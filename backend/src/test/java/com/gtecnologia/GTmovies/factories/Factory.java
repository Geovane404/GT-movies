package com.gtecnologia.GTmovies.factories;

import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.dtos.UserInsertDTO;
import com.gtecnologia.GTmovies.entities.Genre;
import com.gtecnologia.GTmovies.entities.Movie;
import com.gtecnologia.GTmovies.entities.Review;
import com.gtecnologia.GTmovies.entities.User;

public class Factory {
	
	public static Movie createMovie() {
		
		return new Movie(1L, "Homen de ferro", "Lançamento", 2008, "https://www.youtube.com/watch?v=s7MHXDEdjS0",
				"Tony Stark é um industrial bilionário e inventor brilhante que realiza ...", createGenre());
	}
	
	public static Genre createGenre() {
		
		return new Genre(1L, "Comédia");
	}

	public static Review createReview() {
		
		return new Review(1L, "Top!", createMovie(), createUser());
	}
	
	public static User createUser() {
		
		return new User(1L, "João", "joao@gmail.com", "123456");
	}
	
	public static UserInsertDTO createUserInsertDto() {
		
		return new UserInsertDTO(null, "João", "joao@gmail.com", "123456");
	}

	public static ReviewDTO createReviewDto() {
		
		return new ReviewDTO(createReview());
	}
}
