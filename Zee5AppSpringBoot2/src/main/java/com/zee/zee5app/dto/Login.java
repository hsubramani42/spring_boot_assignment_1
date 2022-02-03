package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login")
public class Login {
	@Id
	@Email
	private String userName;

	@Size(max = 100)
	@NotBlank
	private String password;

	@OneToOne
	@JoinColumn(name = "regId", nullable = false, foreignKey = @ForeignKey(name = "fk_logregId"))
	private Register register;

}
