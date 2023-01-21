package com.gtecnologia.GTmovies.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gtecnologia.GTmovies.dtos.RoleDTO;
import com.gtecnologia.GTmovies.dtos.UserDTO;
import com.gtecnologia.GTmovies.dtos.UserInsertDTO;
import com.gtecnologia.GTmovies.entities.User;
import com.gtecnologia.GTmovies.repositories.RoleRepository;
import com.gtecnologia.GTmovies.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public UserDTO newUser(UserInsertDTO dto) {
		
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		user.getRoles().clear();
		
		for(RoleDTO roles : dto.getRoles()) {
			user.getRoles().add(roleRepository.getOne(roles.getId()));
		}
		user = userRepository.save(user);
		return new UserDTO(user);
	}
	
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
