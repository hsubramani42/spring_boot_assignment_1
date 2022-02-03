package com.zee.zee5app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.enums.EROLE;
import com.zee.zee5app.dto.enums.GENRE;
import com.zee.zee5app.dto.enums.LANGUAGE;
import com.zee.zee5app.dto.enums.PLAN_AUTORENEWAL;
import com.zee.zee5app.dto.enums.PLAN_STATUS;
import com.zee.zee5app.dto.enums.PLAN_TYPE;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.RecordExistsException;
import com.zee.zee5app.repository.RoleRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.EpisodeService;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.utils.PasswordUtils;

@SpringBootApplication
public class Testing {

	public static void main(String[] args) {
		Random r = new Random();
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Testing.class, args);
		UserRepository user = applicationContext.getBean(UserRepository.class);
		PasswordUtils passwordUtils = applicationContext.getBean(PasswordUtils.class);

		SeriesService seriesService = applicationContext.getBean(SeriesService.class);
		EpisodeService episodeService = applicationContext.getBean(EpisodeService.class);

		UserService userService = applicationContext.getBean(UserService.class);

		// Add Role

		Role role = new Role();
		role.setRoleName(EROLE.ROLE_USER);

		Role role1 = new Role();
		role1.setRoleName(EROLE.ROLE_ADMIN);

		RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
		roleRepository.deleteByRoleName(EROLE.ROLE_ADMIN);
		roleRepository.save(role);
		roleRepository.save(role1);

//		

		// Add Register & Login
		int i = 1;
		Register register = new Register("ZEE" + "0".repeat(7 - String.valueOf(i).length()) + i, "User", "-" + i,
				"user" + i + "@gmail.com", passwordUtils.generateSecurePassword("user@" + i, passwordUtils.getSalt(20)),
				new BigDecimal("927567482" + i), null, null, null);
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findById(1).get());
		register.setRoles(roles);
		try {
			System.out.println("Adding: " + userService.addUser(register));
		} catch (RecordExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Adding Existing: ");
		try {
			System.out.println(userService.addUser(register));
		} catch (RecordExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//

		SubscriptionService subscriptionService = applicationContext.getBean(SubscriptionService.class);

		int planAmount = 299;
		try {
			System.out.println("Adding subsription-1: " + subscriptionService.addSubscription(new Subscription(
					"SUB" + "0".repeat(7 - String.valueOf(i).length()) + i, new Date(), new Date(), planAmount,
					PLAN_STATUS.values()[r.nextInt(2)], PLAN_TYPE.values()[planAmount == 299 ? 0 : 1],
					PLAN_AUTORENEWAL.values()[r.nextInt(2)], userService.getUserById(register.getId()).get())));
			;
		} catch (RecordExistsException | IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//
		try {
			System.out.println("Adding subsription-2: " + subscriptionService.addSubscription(new Subscription(
					"SUB" + "0".repeat(7 - String.valueOf(i).length()) + (++i), new Date(), new Date(), planAmount,
					PLAN_STATUS.values()[r.nextInt(2)], PLAN_TYPE.values()[planAmount == 299 ? 0 : 1],
					PLAN_AUTORENEWAL.values()[r.nextInt(2)], userService.getUserById(register.getId()).get())));
			;
		} catch (RecordExistsException | IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		i = 1;
		Series series = null;
		try {
			series = new Series("SER" + "0".repeat(7 - String.valueOf(i).length()) + i, r.nextInt(12, 21),
					"Series-" + i, "cast-1, cat-2", GENRE.values()[r.nextInt(6)], "https://youtube.com/trailer_" + i,
					new Date(), LANGUAGE.values()[r.nextInt(5)], null);
			System.out.println("Adding Series: " + seriesService.addSeries(series));
		} catch (RecordExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("Adding episode 1: "
					+ episodeService.addEpisode(new Episode("EPI" + "0".repeat(7 - String.valueOf(i).length()) + i,
							"NAME - " + i, r.nextInt(600, 7200), "India", "https://youtube.com/episode_" + i,
							seriesService.getSeriesById(series.getId()).get())));
		} catch (RecordExistsException | IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("Adding episode 2: "
					+ episodeService.addEpisode(new Episode("EPI" + "0".repeat(7 - String.valueOf(i).length()) + (++i),
							"NAME - " + i, r.nextInt(600, 7200), "India", "https://youtube.com/episode_" + i,
							seriesService.getSeriesById(series.getId()).get())));
		} catch (RecordExistsException | IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		applicationContext.close();
	}

}
