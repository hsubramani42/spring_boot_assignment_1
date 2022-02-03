//package com.zee.zee5app;
//
//import java.util.Random;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import com.zee.zee5app.dto.Subscription;
//import com.zee.zee5app.dto.enums.PLAN_AUTORENEWAL;
//import com.zee.zee5app.dto.enums.PLAN_STATUS;
//import com.zee.zee5app.dto.enums.PLAN_TYPE;
//import com.zee.zee5app.exception.IdNotFoundException;
//import com.zee.zee5app.service.SubscriptionService;
//import com.zee.zee5app.service.impl.SubscriptionServiceImpl;
//
//@SpringBootApplication
//public class TestSubscription {
//
//	public static void main(String[] args) {
//
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(TestSubscription.class, args);
//		SubscriptionService subscriptionService = applicationContext.getBean(SubscriptionServiceImpl.class);
//		Random r = new Random();
//		Date date = new Date();
//		for (int i = 1; i <= 6; i++) {
//			System.out.print("Adding SUB" + "0".repeat(7 - String.valueOf(i).length()) + i + ": ");
//			int planAmount = new int[] { 299, 499 }[r.nextInt(2)];
//			Subscription subscription = new Subscription("SUB" + "0".repeat(7 - String.valueOf(i).length()) + i, date,
//					date, planAmount, PLAN_STATUS.values()[r.nextInt(2)], PLAN_TYPE.values()[planAmount == 299 ? 0 : 1],
//					PLAN_AUTORENEWAL.values()[r.nextInt(2)], "ZEE0000006");
//			System.out.println(subscriptionService.addSubscription(subscription));
//		}
//
//		// Checking object
//		System.out.print("Checking SUB0000006: ");
//		try {
//			System.out.println(subscriptionService.getSubscriptionById("SUB0000006").isPresent());
//		} catch (IdNotFoundException e1) {
//			e1.printStackTrace();
//		}
//
//		// Valid Object
//		Subscription subscriptionRef = new Subscription("SUB0000003", date, date, 299, PLAN_STATUS.inactive,
//				PLAN_TYPE.monthly, PLAN_AUTORENEWAL.no, "ZEE0000003");
//
//		// Updating the object
//		System.out.print("Updating SUB0000003: ");
//		try {
//			System.out.println(subscriptionService.updateSubscriptionById("SUB0000003", subscriptionRef));
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Deleting the object
//		System.out.print("Deleting SUB0000006: ");
//		try {
//			System.out.println(subscriptionService.deleteSubscriptionById("SUB0000006"));
//		} catch (IdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Checking the
//		System.out.println("Checking SUB0000006: ");
//		try {
//			System.out.println(subscriptionService.getSubscriptionById("SUB0000006").isPresent());
//		} catch (IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		System.out.println("Subscriptions List: ");
//		List<Subscription> subscriptionsList = subscriptionService.getAllSubscriptionsList();
//		if (subscriptionsList.size() == 0)
//			System.out.println("Empty Records!");
//		subscriptionsList.forEach(Subscription -> System.out.println(Subscription));
//
//		System.out.println("Subscriptions Array: ");
//		Subscription subscriptionArray[] = subscriptionService.getAllSubscriptions();
//		for (Subscription Subscription : subscriptionArray)
//			System.out.println(Subscription);
//
//		applicationContext.close();
//	}
//
//}
