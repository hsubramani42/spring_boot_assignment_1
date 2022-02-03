package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.exception.RecordExistsException;

public interface MovieService {
	public String addMovie(Movie movie) throws RecordExistsException;

	public String updateMovieById(String id, Movie movie) throws IdNotFoundException;

	public String deleteMovieById(String id) throws IdNotFoundException;

	public Optional<Movie> getMovieById(String id) throws IdNotFoundException;

	public List<Movie> getAllMoviesList();

	public Movie[] getAllMovie();

	public List<Movie> getMovieByName(String name) throws NameNotFoundException;

}
