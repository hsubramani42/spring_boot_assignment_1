package com.zee.zee5app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.enums.EROLE;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Transactional
	void deleteByRoleName(EROLE role);

	Boolean existsByRoleName(EROLE role);
}
