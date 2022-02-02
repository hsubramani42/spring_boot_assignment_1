package com.zee.zee5app;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.impl.LoginServiceImpl;
import com.zee.zee5app.service.impl.UserServiceImpl;
import com.zee.zee5app.utils.PasswordUtils;

@SpringBootApplication
public class TestUser {

	static Scanner hs = new Scanner(System.in);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(TestUser.class, args);
		UserService userService = applicationContext.getBean(UserServiceImpl.class);
		LoginService loginService = applicationContext.getBean(LoginServiceImpl.class);

		PasswordUtils passwordUtils = applicationContext.getBean("passwordUtils", PasswordUtils.class);
		// adding a record
		for (int i = 1; i <= 6; i++) {
			System.out.print("Adding ZEE" + "0".repeat(7 - String.valueOf(i).length()) + i + ": ");
			Register register = new Register("ZEE" + "0".repeat(7 - String.valueOf(i).length()) + i, "User", "-" + i,
					"user" + i + "@gmail.com",
					passwordUtils.generateSecurePassword("user@" + i, passwordUtils.getSalt(20)),
					new BigDecimal("9275674829"));
			System.out.println(userService.addUser(register));
		}

		// fetch a record by id
		System.out.print("Fetching ZEE0000006: ");
		Optional<Register> registeropt;
		try {
			registeropt = userService.getUserById("ZEE0000006");
			System.out.println(registeropt.isPresent());
		} catch (IdNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		// update a register by id
		System.out.print("Updating ZEE0000005: ");
		Register register = new Register("ZEE0000006", "Ramesh", "Mahendran", "ramesh@gmail.com", "ramesh@2000",
				new BigDecimal("9275674829"));
		try {
			System.out.println(userService.updateUserById("ZEE0000005", register));
		} catch (IdNotFoundException e2) {
			e2.printStackTrace();
		}

		// delete a record by id
		System.out.print("Deleting ZEE0000006: ");
		try {
			String status = userService.deleteUserById("ZEE0000006");
			System.out.println(status);
		} catch (IdNotFoundException e1) {
			e1.printStackTrace();
		}

		// fetch a record by id
		System.out.print("Fetching ZEE0000006: ");
		try {
			registeropt = userService.getUserById("ZEE0000006");
			System.out.println(registeropt.isPresent());
		} catch (IdNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		// Get all registers data as a list
		System.out.println("Register List: ");
		Optional<List<Register>> registerList = userService.getAllUsersList();
		if (registerList.get().size() != 0)
			registerList.get().forEach((record) -> System.out.println(record));
		else
			System.out.println("Empty records.");

		// Get all registers data as an array
		System.out.println("Register Array: ");
		Register[] registerArray = userService.getAllUsers();
		if (registerArray.length != 0)
			for (Register reg : registerArray)
				System.out.println(reg);
		else
			System.out.println("Empty records.");

		registerList.get().forEach((record) -> {
			System.out.print("Adding Login for " + record.getId() + ": ");
			Login login = new Login();
			login.setUserName(record.getEmail());
			login.setPassword(record.getPassword());
			login.setRole(ROLE.ROLE_USER);
			login.setRegID(record.getId());
			System.out.println(loginService.addCredentials(login));
		});

		// trying to change password
		System.out.print("Updating user1@gmail.com password: ");
		try {
			System.out.println(loginService.changePassword("user1@gmail.com",
					passwordUtils.generateSecurePassword("newPass", passwordUtils.getSalt(30))));
		} catch (IdNotFoundException e2) {
			e2.printStackTrace();
		}

		// trying to update role
		System.out.print("Updating user2@gmail.com role: ");
		try {
			System.out.println(loginService.changeRole("user2@gmail.com", ROLE.ROLE_ADMIN));
		} catch (IdNotFoundException e2) {
			e2.printStackTrace();
		}

		System.out.print("Deleting user1@gmail.com: ");
		try {
			System.out.println(loginService.deleteCredentials("user1@gmail.com"));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}

		System.out.print("Deleting user1@gmail.com: ");
		try {
			System.out.println(loginService.deleteCredentials("user1@gmail.com"));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}

		applicationContext.close();
	}
}
