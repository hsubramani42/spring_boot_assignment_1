package com.zee.zee5app;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.enums.GENRE;
import com.zee.zee5app.dto.enums.LANGUAGE;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.impl.MovieServiceImpl;

@SpringBootApplication
public class TestMovie {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(TestMovie.class, args);
		MovieService movieService = applicationContext.getBean(MovieServiceImpl.class);

		String movieNames[] = { "A", "B", "C", "D", "E" };
		Random rand = new Random();
		for (int i = 1; i <= 6; i++) {
			System.out.print("Adding MOV" + "0".repeat(7 - String.valueOf(i).length()) + i + ": ");
			Movie movie = new Movie("MOV" + "0".repeat(7 - String.valueOf(i).length()) + i, movieNames[rand.nextInt(5)],
					rand.nextInt(12, 21), GENRE.values()[rand.nextInt(6)], rand.nextInt(6000, 10600), new Date(),
					"cast-1, cat-2", LANGUAGE.values()[rand.nextInt(5)],
					"https://www.youtube.com/movie_" + rand.nextInt(06, 0600000));
			System.out.println(movieService.addMovie(movie));
		}

		// Checking the
		System.out.print("Checking MOV0000006: ");
		try {
			System.out.println(movieService.getMovieById("MOV0000006").isPresent());
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}

		// Updating the object

		int i = 2;
		System.out.print("Updating MOV000000" + i + ": ");
		try {
			// Valid Object
			Movie movieRef = new Movie("MOV" + "0".repeat(7 - String.valueOf(i).length()) + i, "Modified Name",
					rand.nextInt(12, 21), GENRE.values()[rand.nextInt(6)], rand.nextInt(6000, 10600), new Date(),
					"cast-1, cat-2", LANGUAGE.values()[rand.nextInt(5)],
					"https://www.youtube.com/movie_" + rand.nextInt(06, 0600000));
			System.out.println(movieService.updateMovieById("MOV000000" + i, movieRef));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}

		// Deleting the object
		System.out.print("Deleting MOV0000006: ");
		try {
			System.out.println(movieService.deleteMovieById("MOV0000006"));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}

		// Checking the
		System.out.println("Checking MOV0000006: ");
		try {
			System.out.println(movieService.getMovieById("MOV0000006"));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Movies List: ");
		List<Movie> moviesList = movieService.getAllMoviesList();
		if (moviesList.size() == 0)
			System.out.println("Empty records");
		moviesList.forEach(movie -> System.out.println(movie));

		String movieName = "";
		System.out.println("Movies Array: ");
		Movie[] moviesArray = movieService.getAllMovie();
		if (moviesArray.length == 0)
			System.out.println("Empty records");
		for (Movie movie : moviesArray) {
			movieName = movie.getName();
			System.out.println(movie);
		}

		System.out.println("Movies with Name 100: ");
		try {
			List<Movie> moviesListByName = movieService.getMovieByName("100");
			if (moviesListByName.size() == 0)
				System.out.println("Empty records");
			moviesListByName.forEach(movie -> System.out.println(movie));
		} catch (NameNotFoundException e1) {
			e1.printStackTrace();
		}
		System.out.println("Movies with Name " + movieName + ": ");
		try {
			movieService.getMovieByName(movieName).forEach(movie -> System.out.println(movie));
		} catch (NameNotFoundException e1) {
			e1.printStackTrace();
		}
		applicationContext.close();
	}

}
