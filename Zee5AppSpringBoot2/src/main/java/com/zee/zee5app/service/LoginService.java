package com.zee.zee5app.service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.enums.EROLE;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.RecordExistsException;

public interface LoginService {

	public String addCredentials(Login login) throws RecordExistsException;

	public String deleteCredentials(String userName) throws IdNotFoundException;

	public String changePassword(String userName, String password) throws IdNotFoundException;

}
