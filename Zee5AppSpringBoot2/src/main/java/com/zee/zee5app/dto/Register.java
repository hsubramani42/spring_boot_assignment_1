package com.zee.zee5app.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "register")
public class Register implements Comparable<Register> {
	@Id
	@Column(name = "regId")
	@Size(min = 6, max = 10)
	private String id;

	@Size(max = 40)
	@NotBlank
	private String firstName;

	@Size(max = 30)
	private String lastName;

	@Size(max = 50)
	@NotBlank
	@Email
	private String email;

	@Size(max = 100)
	@NotBlank
	private String password;

	@NotNull
	@Range(min = 1000000000, max = 9999999999L)
	private BigDecimal contactNumber;

	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "regId", foreignKey = @ForeignKey(name = "fk_regid")), inverseJoinColumns = @JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "fk_roleid")))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "register", cascade = CascadeType.ALL)
	private Set<Subscription> subscriptions = new HashSet<>();

	@OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
	private Login login;

	@Override
	public int compareTo(Register obj) {
		return this.id.compareTo(obj.id);
	}

}
