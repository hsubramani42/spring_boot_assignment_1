package com.zee.zee5app.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zee.zee5app.dto.enums.PLAN_AUTORENEWAL;
import com.zee.zee5app.dto.enums.PLAN_STATUS;
import com.zee.zee5app.dto.enums.PLAN_TYPE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Entity
@Table(name = "subscription")
public class Subscription implements Comparable<Subscription> {

	@Id
	@Size(max = 10, min = 6)
	private String id;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateOfPurchase;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@NotNull
	private float amount;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PLAN_STATUS status;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PLAN_TYPE type;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PLAN_AUTORENEWAL autoRenewal;

	@ManyToOne
	@JoinColumn(name = "registerId", nullable = false)
	private Register register;

	@Override
	public int compareTo(Subscription obj) {
		return this.id.compareTo(obj.id);
	}

}
