package com.gtecnologia.GTmovies.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gtecnologia.GTmovies.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandarError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandarError er = new StandarError();

		er.setTimestamp(Instant.now());
		er.setStatus(status.value());
		er.setError("Resource not found!");
		er.setMessage(e.getMessage());
		er.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(er);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationException(MethodArgumentNotValidException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError er = new ValidationError();

		er.setTimestamp(Instant.now());
		er.setStatus(status.value());
		er.setError("Validation!");
		er.setMessage(null);
		er.setPath(request.getRequestURI());
		
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			
			er.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(er);
	}
}
