package com.gtecnologia.GTmovies.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.gtecnologia.GTmovies.dtos.MovieDTO;
import com.gtecnologia.GTmovies.dtos.ReviewDTO;
import com.gtecnologia.GTmovies.factories.Factory;
import com.gtecnologia.GTmovies.factories.TokenUtil;
import com.gtecnologia.GTmovies.services.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService movieService;

	@Autowired
	private TokenUtil tokenUtil;

	private String username;
	private String password;
	private long existingId;

	private List<MovieDTO> listMovieDto;
	private List<ReviewDTO> listReviewDto;
	private PageImpl<MovieDTO> pageMovieDto;
	private PageImpl<ReviewDTO>pageReviewDto;
	
	private MovieDTO movieDto;
	private ReviewDTO reviewDto;

	@BeforeEach
	void setUp() throws Exception {

		username = "ana@gmail.com";
		password = "123456";
		existingId = 1L;

		movieDto = Factory.createMovieDto();
		reviewDto = Factory.createReviewDto();
		
		listMovieDto = new ArrayList<>();
		listMovieDto.add(movieDto);
		
		listReviewDto = new ArrayList<>();
		listReviewDto.add(reviewDto);
		
		pageMovieDto = new PageImpl<>(List.of(movieDto));
		pageReviewDto = new PageImpl<>(List.of(reviewDto));

		when(movieService.findListMovie()).thenReturn(listMovieDto);
		when(movieService.findMovieById(existingId)).thenReturn(movieDto);
		when(movieService.findPageMovieByGenre(any(), any())).thenReturn(pageMovieDto);
		when(movieService.findPageReviewByMovie(any(), any())).thenReturn(pageReviewDto);
		when(movieService.findListReviewByMovie(any())).thenReturn(listReviewDto);
	}

	@Test
	public void findListMovieShouldReturnListMovieDto() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

		ResultActions result = mockMvc.perform(get("/movies")
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
	}

	@Test
	public void findMovieByIdShouldReturnMovieDto() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

		ResultActions result = mockMvc.perform(get("/movies/{id}", existingId)
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.title").exists());
		result.andExpect(jsonPath("$.year").exists());
		result.andExpect(jsonPath("$.genre").exists());
	}
	
	@Test
	public void findPageMovieByGenreShouldReturnPageMovieDto()throws Exception{
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

		ResultActions result = mockMvc.perform(get("/movies/page")
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON));
				
		result.andExpect(status().isOk());
	}
	
	@Test
	public void findPageReviewByMovieShouldReturnPageReviewDto()throws Exception{
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

		ResultActions result = mockMvc.perform(get("/movies/{idMovie}/reviews", existingId)
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON));
				
		result.andExpect(status().isOk());
	}
	
	@Test
	public void findListReviewByMovieShouldReturnListReviewDto()throws Exception{
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

		ResultActions result = mockMvc.perform(get("/movies/{idMovie}/reviews/list", existingId)
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON));
				
		result.andExpect(status().isOk());
	}
}
