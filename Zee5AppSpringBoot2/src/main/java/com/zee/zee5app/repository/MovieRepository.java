package com.zee.zee5app.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.enums.LANGUAGE;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
	Boolean existsByName(String name);

	Boolean existsByTrailer(String trailer);

	Optional<Movie> findByNameAndLanguage(String name, LANGUAGE language);

	List<Movie> findAllByName(String name);

	Optional<Movie> findByNameAndReleaseDate(String name, Date releaseDate);

	List<Movie> findByCastIgnoreCaseContaining(String cast);

}
