//package com.zee.zee5app;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import com.zee.zee5app.dto.Series;
//import com.zee.zee5app.dto.enums.GENRE;
//import com.zee.zee5app.dto.enums.LANGUAGE;
//import com.zee.zee5app.exception.IdNotFoundException;
//import com.zee.zee5app.service.SeriesService;
//import com.zee.zee5app.service.impl.SeriesServiceImpl;
//
//@SpringBootApplication
//public class TestSeries {
//
//	public static void main(String[] args) {
//
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(TestSeries.class, args);
//		SeriesService seriesService = applicationContext.getBean(SeriesServiceImpl.class);
//		Random rand = new Random();
//		for (int i = 1; i <= 6; i++) {
//			System.out.print("Adding SER" + "0".repeat(7 - String.valueOf(i).length()) + i + ": ");
//			Series series = new Series("SER" + "0".repeat(7 - String.valueOf(i).length()) + i, rand.nextInt(12, 21),
//					"Series-" + i, "cast-1, cat-2", GENRE.values()[rand.nextInt(6)], "https://youtube.com/trailer_" + i,
//					new Date(), LANGUAGE.values()[rand.nextInt(5)], 1);
//			System.out.println(seriesService.addSeries(series));
//
//		}
//
//		// Checking the
//		System.out.print("Checking SER0000006: ");
//		try {
//			System.out.println(seriesService.getSeriesById("SER0000006").isPresent());
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Updating the object
//		System.out.print("Updating SER0000002: ");
//		try {
//			int i = 2;
//			Series seriesRef = new Series("SER000000" + i, rand.nextInt(12, 21), "Modified Name", "cast-1, cat-2",
//					GENRE.values()[rand.nextInt(6)], "https://youtube.com/trailer_modified_" + i, new Date(),
//					LANGUAGE.values()[rand.nextInt(5)], 1);
//			System.out.println(seriesService.updateSeriesById("SER000000" + i, seriesRef));
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Deleting the object
//		System.out.print("Deleting SER0000006: ");
//		try {
//			System.out.println(seriesService.deleteSeriesById("SER0000006"));
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Checking the
//		System.out.println("Checking SER0000006: ");
//		try {
//			System.out.println(seriesService.getSeriesById("SER0000006"));
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Seriess List: ");
//		List<Series> seriesList = seriesService.getAllSeriesList();
//		if (seriesList.size() == 0)
//			System.out.println("Empty Records");
//		seriesList.forEach(Series -> System.out.println(Series));
//
//		System.out.println("Seriess Array: ");
//		Series[] seriesArray = seriesService.getAllSeries();
//
//		if (seriesArray.length == 0)
//			System.out.println("Empty Records");
//		for (Series Series : seriesArray)
//			System.out.println(Series);
//		applicationContext.close();
//	}
//
//}
