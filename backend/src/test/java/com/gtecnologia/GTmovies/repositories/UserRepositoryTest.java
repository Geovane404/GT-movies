package com.gtecnologia.GTmovies.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gtecnologia.GTmovies.entities.Role;
import com.gtecnologia.GTmovies.entities.User;

@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	private long countTotalUser;
	
	@BeforeEach
	void setUp() throws Exception{
		
		user = new User();
		countTotalUser = userRepository.count();
	}
	
	
	@Test
	public void saveShouldPersistWithAutoIncrementAndReturnUserWhenIdIsNull() {
		
		user.setId(null);
		user.setName("Renê");
		user.setEmail("rene@gmail.com");
		user.setPassword("123456");
		user.getRoles().add(new Role(null, null));
		
		user = userRepository.save(user);
		
		Assertions.assertEquals(countTotalUser + 1, user.getId());
		Assertions.assertNotNull(user);
	}
}
