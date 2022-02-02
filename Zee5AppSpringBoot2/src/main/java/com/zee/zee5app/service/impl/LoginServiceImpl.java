package com.zee.zee5app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository = null;

	@Override
	public String addCredentials(Login login) {
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

	@Override
	public String changeRole(String userName, ROLE role) throws IdNotFoundException {
		Optional<Login> login = this.loginRepository.findById(userName);
		if (login.isEmpty())
			throw new IdNotFoundException("Invalid Id");
		login.get().setRole(role);
		return (this.loginRepository.save(login.get()) != null) ? "success" : "fail";
	}

}
