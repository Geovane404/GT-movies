package com.gtecnologia.GTmovies.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gtecnologia.GTmovies.controllers.exceptions.FieldMessage;
import com.gtecnologia.GTmovies.dtos.UserInsertDTO;
import com.gtecnologia.GTmovies.entities.User;
import com.gtecnologia.GTmovies.repositories.UserRepository;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO>{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		User user = userRepository.findByEmail(dto.getEmail());
		
		if(user != null ) {
			list.add(new FieldMessage("Email", "O email ja esta cadastrado por outro usuário!"));
		}
		
		for(FieldMessage e : list) {
			//direcionamento das exceptions para classe de exceptions customizadas(ResourceExceptionHandler)
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
