package com.gtecnologia.GTmovies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gtecnologia.GTmovies.entities.User;
import com.gtecnologia.GTmovies.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	//UserDetailsService: retornar o usuario por email
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		
		if(user != null) {
			
			return user;
		}
		throw new UsernameNotFoundException("Usuario não cadastrado!");
	}
}
