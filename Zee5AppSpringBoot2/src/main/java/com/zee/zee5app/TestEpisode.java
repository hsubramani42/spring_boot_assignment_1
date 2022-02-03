//package com.zee.zee5app;
//
//import java.util.List;
//import java.util.Random;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import com.zee.zee5app.dto.Episode;
//import com.zee.zee5app.exception.IdNotFoundException;
//import com.zee.zee5app.service.EpisodeService;
//import com.zee.zee5app.service.impl.EpisodeServiceImpl;
//
//@SpringBootApplication
//public class TestEpisode {
//
//	public static void main(String[] args) {
//
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(TestEpisode.class, args);
//		EpisodeService episodeService = applicationContext.getBean(EpisodeServiceImpl.class);
//
//		Random rand = new Random();
//		for (int i = 1; i <= 6; i++) {
//			System.out.print("Adding EPI" + "0".repeat(7 - String.valueOf(i).length()) + i + ": ");
//			Episode episode = new Episode("EPI" + "0".repeat(7 - String.valueOf(i).length()) + i, "SER0000001",
//					"NAME - " + i, rand.nextInt(600, 7200), "India", "https://youtube.com/episode_" + i);
//			System.out.println(episodeService.addEpisode(episode));
//		}
//
//		Episode episodeRef = null;
//
//		episodeRef = new Episode("EPI0000002", "SER0000001", "Modified Name", rand.nextInt(600, 7200), "America",
//				"https://youtube.com/episode_2");
//
//		// Checking the
//		System.out.print("Checking EPI0000006: ");
//		try {
//			System.out.println(episodeService.getEpisodeById("EPI0000006").isPresent());
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Updating the object
//		System.out.print("Updating EPI0000006: ");
//		try {
//			System.out.println(episodeService.updateEpisodeById("EPI0000006", episodeRef));
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Deleting the object
//		System.out.print("Deleting EPI0000006: ");
//		try {
//			System.out.println(episodeService.deleteEpisodeById("EPI0000006"));
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Checking the existence
//		System.out.println("Checking EPI0000006: ");
//		try {
//			System.out.println(episodeService.getEpisodeById("EPI0000006"));
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Episodes List: ");
//		List<Episode> episodeList = episodeService.getAllEpisodeList();
//		if (episodeList.size() == 0)
//			System.out.println("Empty Records");
//		episodeList.forEach(Episode -> System.out.println(Episode));
//
//		System.out.println("Episodes Array: ");
//		Episode[] episodeArray = episodeService.getAllEpisode();
//		if (episodeArray.length == 0)
//			System.out.println("Empty Records");
//		for (Episode Episode : episodeArray)
//			System.out.println(Episode);
//
//		applicationContext.close();
//	}
//
//}
