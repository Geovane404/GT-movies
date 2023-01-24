package com.gtecnologia.GTmovies.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.factories.Factory;
import com.gtecnologia.GTmovies.factories.TokenUtil;
import com.gtecnologia.GTmovies.services.ReviewService;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ReviewService reviewService;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private ReviewDTO reviewDTO;
	
	private String username;
	private String password;
	
	@BeforeEach
	void setUp()throws Exception{
		
		reviewDTO = Factory.createReviewDto();
		
		username = "ana@gmail.com";
		password = "123456";
		
		when(reviewService.postNewReview(any())).thenReturn(reviewDTO);
	}

	@Test
	public void postNewReviewShouldReturnReviewDto()throws Exception{
		
		String jsonBody = objectMapper.writeValueAsString(reviewDTO);
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

		ResultActions result = mockMvc.perform(post("/reviews")
				.header("Authorization", "Bearer " + accessToken)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
	}
}
