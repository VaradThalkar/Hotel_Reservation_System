package com.edubridge.hms.Service;

import java.sql.SQLException;
import java.util.Scanner;

import com.edubridge.hms.Repository.*;
import com.edubridge.hms.Utilities.IsValidName;
import com.edubridge.hms.model.*;

public class UserService {
	public UserRepository userRepository = new UserRepository();
	public UserDetails userDetails = new UserDetails();
	public Hotel hotel = new Hotel();

	public void userRegistration() throws SQLException {
		// TODO Auto-generated method stub
		// User Registration Form
		Scanner scanner = new Scanner(System.in);
		String userName, password, confirmPassword, name = null;
		boolean loopUserName = true, loopPassword = true, loopName = true, loopContactNumber = true,
				loopAdhaarNumber = true;
		long contactNumber = 0, adhaarNumber = 0;
		System.out.println("----------User Registration----------");
		do {
			System.out.print("Enter username:");
			userName = scanner.next();
			if (userName.isEmpty()) {
				System.out.println("username cannot be empty");
				loopUserName = true;
			} else {
				loopUserName = false;
			}
		} while (loopUserName == true);
		do {
			System.out.print("Enter Password:");
			password = scanner.next();
			System.out.print("Re enter te password for confirmation:");
			confirmPassword = scanner.next();
			if (password.equals(confirmPassword)) {
				System.out.println("Password Confirmed.....");
				do {
					System.out.print("Enter your Name:");
					name = scanner.next();
					IsValidName ivn = new IsValidName();
					if (IsValidName.isValidname(name) == true) {
						loopName = false;
					} else {
						loopName = true;
						System.out.println("Enter a valid name");
					}
				} while (loopName == true);
				do {
					System.out.print("Enter ContactNumber:");
					contactNumber = scanner.nextLong();
					if (Long.toString(contactNumber).length() == 10) {
						loopContactNumber = false;
					} else {
						loopContactNumber = true;
						System.out.println("Enter a valid contact number");
					}
				} while (loopContactNumber == true);
				do {
					System.out.print("Enter AdhaarNumber:");
					adhaarNumber = scanner.nextLong();
					if (Long.toString(adhaarNumber).length() == 12) {
						loopAdhaarNumber = false;
					} else {
						loopAdhaarNumber = true;
						System.out.println("Enter a valid Aadhar number");
					}
				} while (loopAdhaarNumber == true);
				loopPassword = false;
			} else {
				System.out.println("Both passwords should be same!");
				loopPassword = true;
			}
		} while (loopPassword == true);
		userDetails.setUserName(userName);
		userDetails.setPassword(password);
		userDetails.setName(name);
		userDetails.setContactNumber(contactNumber);
		userDetails.setAdhaarNumber(adhaarNumber);
		UserDetails user = userRepository.createUser(userDetails);
		userDesk(user.getUserId());
	}

	public void userLogin() throws SQLException {
		// User login
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		String userName, password;
		System.out.println("----------User Login----------");
		do {
			System.out.print("Enter UserName:");
			userName = scanner.next();
			System.out.print("Enter Password:");
			password = scanner.next();
			UserDetails user = userRepository.viewUserByUserName(userName);
			if (user.getPassword().equals(password)) {
				userDesk(user.getUserId());
			} else if (userName.equals("Admin") && password.equals("Admin")) {
				adminDesk();
			} else {
				System.out.println("Invalid login details!!");
				loop = true;
			}
		} while (loop = true);
	}

	public void userDesk(int userId) throws SQLException {
		ReservationService reservationService = new ReservationService();
		System.out.println("----------User Desk----------");
		boolean loop = false;
		do {
			System.out.println("1.Create a new reservation" + "\n2.Update an existing reservation"
					+ "\n3.View reservations" + "\n4.cancel a reservation" + "\n5.Logout");
			System.out.print("Enter a valid choice:");
			Scanner scan = new Scanner(System.in);
			int c1 = scan.nextInt();
			if (c1 == 1) {
				ReservationService.createReservation(userId);
				loop = true;
			} else if (c1 == 2) {
				ReservationService.updateReservationByUserId(userId);
				loop = true;
			} else if (c1 == 3) {
				ReservationService.viewReservationByUserId(userId);
				loop = true;
			} else if (c1 == 4) {
				ReservationService.cancelReservation(userId);
				loop = true;
			} else if (c1 == 5) {
				System.out.println("Thank you!");
				loop = false;
			} else {
				System.out.println("Incorrect Choice.... please Try again");
			}
		} while (loop == true);
	}

	public void adminDesk() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Admin Desk----------");
		boolean loop = true;
		int input = 0;
		do {
			System.out.println("1.Create Hotel");
			System.out.println("2.View Hotel");
			System.out.println("3.Update Hotel");
			System.out.println("4.Delete Hotel");
			System.out.println("5.view Reservation");
			System.out.println("6.update Reservation");
			System.out.println("7.Delete Reservation");
			System.out.println("8.Logout");
			System.out.print("Enter a valid Choice");
			input = scanner.nextInt();
			switch (input) {
			case 1:
				HotelService.createHotel();
				break;
			case 2:
				HotelService.viewHotel();
				break;
			case 3:
				HotelService.updateHotel();
				break;
			case 4:
				HotelService.deleteHotel();
				break;
			case 5:
				ReservationService.viewAllReservation();
				break;
			case 6:
				ReservationService.updateReservation();
				break;
			case 7:
				ReservationService.deleteReservation();
				break;
			case 8:
				loop = false;
				break;
			default:
				System.out.println("Invalid input!!!");
				loop = true;
				break;
			}
		} while (loop == true);
	}
}
