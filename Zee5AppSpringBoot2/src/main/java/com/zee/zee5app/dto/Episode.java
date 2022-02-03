package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "trailer" }), name = "episode")
public class Episode {

	@Id
	@Size(min = 6, max = 10)
	private String epiId;

	@NotBlank
	private String episodename;

	@Min(600)
	@NotNull
	private int length;

	@NotBlank
	@Size(max = 30)
	private String location;

	@URL
	private String trailer;

	@ManyToOne
	@JoinColumn(name = "seriesId", nullable = false, foreignKey = @ForeignKey(name = "fk_episerId"))
	private Series series;

}
