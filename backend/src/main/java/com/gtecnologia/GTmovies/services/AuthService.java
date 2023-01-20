package com.gtecnologia.GTmovies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.stereotype.Service;

import com.gtecnologia.GTmovies.entities.User;
import com.gtecnologia.GTmovies.repositories.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User authenticated() {
		
		try {
			
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(userName);
		} catch (Exception e) {
			
			throw new  UnauthorizedClientException("Usuário invalido!");
		}
	}
}
