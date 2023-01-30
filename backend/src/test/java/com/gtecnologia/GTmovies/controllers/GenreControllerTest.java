package com.gtecnologia.GTmovies.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.gtecnologia.GTmovies.dtos.GenreDTO;
import com.gtecnologia.GTmovies.factories.Factory;
import com.gtecnologia.GTmovies.factories.TokenUtil;
import com.gtecnologia.GTmovies.services.GenreServices;


@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GenreServices genreServices;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	private String username;
	private String password;
	
	private GenreDTO genreDTO;
	private List<GenreDTO> listGenreDto;

	
	@BeforeEach
	void setUp()throws Exception{
		
		username = "ana@gmail.com";
		password = "123456";
		
		genreDTO = Factory.createGenreDto();
		listGenreDto = new ArrayList<>();
		listGenreDto.add(genreDTO);
		
		Mockito.when(genreServices.allGenres()).thenReturn(listGenreDto);
	}
	
	@Test
	public void allGenresShouldReturnListGenreDto() throws Exception {
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		ResultActions result = mockMvc.perform(get("/genres")
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
}
