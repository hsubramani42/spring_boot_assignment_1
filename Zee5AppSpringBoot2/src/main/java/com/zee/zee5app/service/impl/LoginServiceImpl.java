package com.zee.zee5app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.RecordExistsException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public String addCredentials(Login login) throws RecordExistsException {
		if (this.loginRepository.existsById(login.getUserName()))
			throw new RecordExistsException("Email Id exists!");
		return (this.loginRepository.save(login) != null) ? "success" : "fail";
	}

	@Override
	public String deleteCredentials(String userName) throws IdNotFoundException {
		if (!this.loginRepository.existsById(userName))
			throw new IdNotFoundException("Invalid Id");
		this.loginRepository.deleteById(userName);
		return "success";
	}

	@Override
	public String changePassword(String userName, String password) throws IdNotFoundException {
		Optional<Login> login = this.loginRepository.findById(userName);
		if (login.isEmpty())
			throw new IdNotFoundException("Invalid Id");
		login.get().setPassword(password);
		return (this.loginRepository.save(login.get()) != null) ? "success" : "fail";

	}
}
