package com.zee.zee5app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.enums.EROLE;
import com.zee.zee5app.exception.RoleNotFoundException;
import com.zee.zee5app.repository.RoleRepository;
import com.zee.zee5app.service.RoleService;

public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public String addRole(Role role) {
		return (this.roleRepository.save(role) != null) ? "success" : "fail";
	}

	@Override
	public void deleteRole(EROLE role) throws RoleNotFoundException {
		if (!this.roleRepository.existsByRoleName(role))
			throw new RoleNotFoundException("Role Not found");
		this.roleRepository.deleteByRoleName(role);
	}

}
