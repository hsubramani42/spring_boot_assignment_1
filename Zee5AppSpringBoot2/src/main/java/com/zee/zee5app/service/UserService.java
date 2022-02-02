package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;

public interface UserService {
	public String addUser(Register register);

	public Optional<Register> getUserById(String id) throws IdNotFoundException;

	public Optional<List<Register>> getAllUsersList();

	public Register[] getAllUsers();

	public String deleteUserById(String id) throws IdNotFoundException;

	public String updateUserById(String id, Register register) throws IdNotFoundException;

}