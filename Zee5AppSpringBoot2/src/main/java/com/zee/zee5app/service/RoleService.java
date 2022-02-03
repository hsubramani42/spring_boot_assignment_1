package com.zee.zee5app.service;

import org.springframework.transaction.annotation.Transactional;

import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.enums.EROLE;
import com.zee.zee5app.exception.RoleNotFoundException;

public interface RoleService {
	@Transactional(rollbackFor = Exception.class)
	public String addRole(Role role);


	void deleteRole(EROLE role) throws RoleNotFoundException;

}
