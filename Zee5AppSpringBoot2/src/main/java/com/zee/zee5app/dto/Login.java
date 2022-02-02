package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zee.zee5app.dto.enums.ROLE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "regID" }), name = "login")
public class Login {
	@Id
	@Email
	private String userName;

	@Size(max = 100)
	@NotBlank
	private String password;

	@NotBlank
	@Size(min = 6, max = 10)
	private String regID;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ROLE role;

}
