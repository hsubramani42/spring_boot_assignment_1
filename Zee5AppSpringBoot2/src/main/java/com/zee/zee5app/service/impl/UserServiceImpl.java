package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.RecordExistsException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginService loginService;

	@Override
	@Transactional(rollbackFor = RecordExistsException.class)
	public String addUser(Register register) throws RecordExistsException {
		if (this.userRepository.existsByEmailOrContactNumber(register.getEmail(), register.getContactNumber()))
			throw new RecordExistsException("Email Id or Contact Numer exists!");
		Register savedRegister = this.userRepository.save(register);
		if (savedRegister == null)
			return "fail";

		Login login = new Login(savedRegister.getEmail(), savedRegister.getPassword(), savedRegister);

		String status = loginService.addCredentials(login);
		if (!status.equals("success"))
			throw new RecordExistsException(status);

		return "success";
	}

	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException {
		Optional<Register> register = this.userRepository.findById(id);
		if (register.isPresent())
			return register;
		else
			throw new IdNotFoundException("Invalid Id");
	}

	@Override
	public Optional<List<Register>> getAllUsersList() {
		return Optional.of(this.userRepository.findAll());
	}

	@Override
	public Register[] getAllUsers() {
		List<Register> registers = this.userRepository.findAll();
		return registers.toArray(new Register[registers.size()]);
	}

	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		if (!this.userRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");

		this.userRepository.deleteById(id);
		return "success";
	}

	@Override
	public String updateUserById(String id, Register register) throws IdNotFoundException {
		if (!this.userRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");

		return (this.userRepository.save(register) != null) ? "success" : "fail";
	}

}
