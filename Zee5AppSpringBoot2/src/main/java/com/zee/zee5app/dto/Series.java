package com.zee.zee5app.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "trailer") }, name = "series")
public class Series implements Comparable<Series> {

	@Id
	@Size(min = 6, max = 10)
	private String id;

	@NotNull
	@Range(min = 2, max = 70)
	private int agelimit;

	@NotBlank
	private String name;

	@NotBlank
	private String cast;

	@NotNull
	@Enumerated(EnumType.STRING)
	private GENRE genre;

	@NotBlank
	private String trailer;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date releaseDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private LANGUAGE language;

	@OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
	private Set<Episode> episodes = new HashSet<>();

	@Override
	public int compareTo(Series obj) {
		return this.id.compareTo(obj.id);
	}

}
