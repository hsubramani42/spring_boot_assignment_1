package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository = null;

	@Override
	public String addUser(Register register) {
		return (this.userRepository.save(register) != null) ? "success" : "fail";
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
