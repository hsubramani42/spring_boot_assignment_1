package com.zee.zee5app.service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.exception.IdNotFoundException;

public interface LoginService {

	public String addCredentials(Login login);

	public String deleteCredentials(String userName) throws IdNotFoundException;

	public String changePassword(String userName,String password) throws IdNotFoundException;
	
	public String changeRole(String userName, ROLE role) throws IdNotFoundException;

}