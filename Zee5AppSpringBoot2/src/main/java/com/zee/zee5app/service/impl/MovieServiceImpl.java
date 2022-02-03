package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.exception.RecordExistsException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public String addMovie(Movie movie) throws RecordExistsException {
		if (this.movieRepository.existsByTrailer(movie.getTrailer()))
			throw new RecordExistsException("Trailer exist");
		return (this.movieRepository.save(movie) != null) ? "success" : "fail";
	}

	@Override
	public String updateMovieById(String id, Movie movie) throws IdNotFoundException {
		if (!this.movieRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");
		return (this.movieRepository.save(movie) != null) ? "success" : "fail";
	}

	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		if (!this.movieRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");
		this.movieRepository.deleteById(id);
		return "success";
	}

	@Override
	public Optional<Movie> getMovieById(String id) throws IdNotFoundException {
		Optional<Movie> movie = this.movieRepository.findById(id);
		if (movie.isEmpty())
			throw new IdNotFoundException("Invalid Id");
		return movie;
	}

	@Override
	public List<Movie> getAllMoviesList() {
		return this.movieRepository.findAll();
	}

	@Override
	public Movie[] getAllMovie() {
		List<Movie> movies = this.movieRepository.findAll();
		return movies.toArray(new Movie[movies.size()]);
	}

	@Override
	public List<Movie> getMovieByName(String name) throws NameNotFoundException {
		List<Movie> movies = this.movieRepository.findAllByName(name);
		if (movies.size() == 0)
			throw new NameNotFoundException("Invalid Name");
		return movies;

	}

}
