package com.gtecnologia.GTmovies.dtos;

import com.gtecnologia.GTmovies.services.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO{
	private static final long serialVersionUID = 1L;
	
	private String password;
	
	public UserInsertDTO() {
		super();
	}
	
	public UserInsertDTO(Long id, String name, String email, String password) {
		super(id, name, email);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
