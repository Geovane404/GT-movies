package com.gtecnologia.GTmovies.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTmovies.dtos.UserDTO;
import com.gtecnologia.GTmovies.dtos.UserInsertDTO;
import com.gtecnologia.GTmovies.factories.Factory;
import com.gtecnologia.GTmovies.repositories.UserRepository;

@SpringBootTest
@Transactional
public class UserServiceIT {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	private UserInsertDTO userInsertDTO;
	private long countTotalUser;
	
	@BeforeEach
	void setUp()throws Exception{
		
		userInsertDTO = Factory.createUserInsertDto();
		countTotalUser = userRepository.count();
	}
	
	@Test
	public void newUserShouldReturnUserDto() {
		
		UserDTO dto = userService.newUser(userInsertDTO);
		
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(countTotalUser + 1, dto.getId());
	}
}
