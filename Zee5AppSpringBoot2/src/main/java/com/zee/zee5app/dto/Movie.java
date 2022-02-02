package com.zee.zee5app.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import com.zee.zee5app.dto.enums.GENRE;
import com.zee.zee5app.dto.enums.LANGUAGE;

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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "trailer" }), name = "movie")
public class Movie implements Comparable<Movie> {

	@Id
	@Size(min = 6, max = 10)
	private String id;

	@NotBlank
	private String name;

	@NotNull
	@Range(min = 2, max = 70)
	private int agelimit;

	@NotNull
	@Enumerated(EnumType.STRING)
	private GENRE genre;

	@NotNull
	@Min(3600)
	private int length;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date releaseDate;

	@NotBlank
	private String cast;

	@NotNull
	@Enumerated(EnumType.STRING)
	private LANGUAGE language;

	@NotBlank
	@URL
	private String trailer;

	@Override
	public int compareTo(Movie obj) {
		return this.id.compareTo(obj.id);
	}
}
