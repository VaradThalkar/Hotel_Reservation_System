package com.edubridge.hms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edubridge.hms.Repository.HotelRepository;
import com.edubridge.hms.Service.*;
import com.edubridge.hms.model.Hotel;

public class MenuOptions {
	static UserService userService = new UserService();

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		// user login,registration,Exit
		// if user logged in?
		/*
		 * if usertype=admin admin attributes(hotel management,user
		 * management,reservation) else usertype=user crud reservation else login,
		 * registration window
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.println("..........Welcome to the Reservation Desk..........");
		System.out.println("1.User Registration");
		System.out.println("2.User Login");
		System.out.println("3.Exit");
		boolean loop = true;
		do {
			System.out.print("Please Enter a valid choice:");
			int input = scanner.nextInt();
			switch (input) {
			case (1):
				userService.userRegistration();
				loop = false;
				break;
			case (2):
				userService.userLogin();
				loop = false;
				break;
			case (3):
				System.out.println("Thank You");
				System.exit(0);
			default:
				loop = true;
				System.out.println("Invalid input");
			}
		} while (loop == true);

	}
}
