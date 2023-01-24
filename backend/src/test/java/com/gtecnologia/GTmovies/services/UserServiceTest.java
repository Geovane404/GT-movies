package com.gtecnologia.GTmovies.services;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gtecnologia.GTmovies.dtos.UserDTO;
import com.gtecnologia.GTmovies.dtos.UserInsertDTO;
import com.gtecnologia.GTmovies.entities.User;
import com.gtecnologia.GTmovies.factories.Factory;
import com.gtecnologia.GTmovies.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	private UserInsertDTO userInsertDTO;
	private User user;
	
	@BeforeEach
	void setUp() throws Exception{
		
		userInsertDTO = Factory.createUserInsertDto();
		user = Factory.createUser();
		
		Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);
		Mockito.when(passwordEncoder.encode(ArgumentMatchers.any())).thenReturn(any());
	}
	
	@Test
	public void newUserShouldReturnUserDto() {
		
		UserDTO dto = userService.newUser(userInsertDTO);
		
		Assertions.assertNotNull(dto);
		Assertions.assertNotEquals(userInsertDTO.getId(), dto.getId());
		Assertions.assertEquals(userInsertDTO.getName(), dto.getName());
	}
}
