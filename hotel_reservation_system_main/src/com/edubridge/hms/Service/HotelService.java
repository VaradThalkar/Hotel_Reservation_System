package com.edubridge.hms.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edubridge.hms.Repository.HotelRepository;
import com.edubridge.hms.Utilities.TableFormat;
import com.edubridge.hms.model.Hotel;

public class HotelService {
	public static HotelRepository hotelRepository = new HotelRepository();
	public static Hotel hotel = new Hotel();

	public static void createHotel() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		String hotelName;
		int NumberOfRooms, AvailableRooms;
		boolean loopHotelName = true, loopNumberOfRooms = true, loopAvailableRooms = true;
		System.out.println("----------Create Hotel----------");
		do {
			System.out.print("Enter Hotel Name:");
			hotelName = scanner.next();
			if (hotelName.isEmpty()) {
				System.out.println("Hotel name cannot be empty!!!");
				loopHotelName = true;
			} else {
				loopHotelName = false;
			}
		} while (loopHotelName == true);
		do {
			System.out.print("Enter Number of Rooms:");
			NumberOfRooms = scanner.nextInt();
			if (NumberOfRooms > 0) {
				loopNumberOfRooms = false;
			} else {
				System.out.println("Number of rooms cant be 0");
				loopNumberOfRooms = true;
			}
		} while (loopNumberOfRooms == true);
		do {
			System.out.print("Enter Number of Available Rooms:");
			AvailableRooms = scanner.nextInt();
			if (AvailableRooms > 0) {
				loopAvailableRooms = false;
			} else {
				System.out.println("Number of rooms cant be 0");
				loopAvailableRooms = true;
			}
		} while (loopAvailableRooms == true);
		hotel.setHotelName(hotelName);
		hotel.setNumberOfRooms(NumberOfRooms);
		hotel.setAvailableRooms(AvailableRooms);
		HotelRepository.createHotel(hotel);
		scanner.close();
	}

	public static void updateHotel() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		String hotelName;
		int choice, hotelId, numberOfRooms, AvailableRooms;
		boolean loopUpdateHotel = true, loopUpdateField = true;
		System.out.println("----------Update Hotel----------");
		do {
			viewHotel();
			System.out.println("Enter Hotel Id to update the Hotel:");
			hotelId = scanner.nextInt();
			if (hotelId > 0) {
				hotel.setHotelid(hotelId);
				Hotel res=HotelRepository.viewHotel(hotel.getHotelid());
				System.out.println(res.toString());
				loopUpdateHotel = false;
			} else {
				loopUpdateHotel = true;
			}
		} while (loopUpdateHotel == true);
		do {
			System.out.println("1.Update Hotel Name");
			System.out.println("2.Update Number of rooms");
			System.out.println("3.update Avaialable rooms");
			System.out.print("Enter a valid Choice:");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter new Hotel Name:");
				hotelName = scanner.next();
				hotel.setHotelName(hotelName);
				loopUpdateField = false;
				break;
			case 2:
				System.out.println("Enter number of rooms:");
				numberOfRooms = scanner.nextInt();
				hotel.setNumberOfRooms(numberOfRooms);
				loopUpdateField = false;
				break;
			case 3:
				System.out.println("Enter Available rooms:");
				AvailableRooms = scanner.nextInt();
				hotel.setAvailableRooms(AvailableRooms);
				loopUpdateField = false;
				break;
			default:
				loopUpdateField = true;
				break;
			}
		} while (loopUpdateField == true);
		HotelRepository.updateHotel(hotel);
		scanner.close();
	}

	public static void viewHotel() throws SQLException {
		List<Hotel> hotels=HotelRepository.viewAllHotel();
		TableFormat.HotelTable(hotels);
	}

	public static void deleteHotel() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		int hotelId;
		boolean loopdeleteHotel = true;
		do {
			System.out.println("----------Delete Hotel----------");
			viewHotel();
			System.out.println("Enter a hotel id to delete:");
			hotelId = scanner.nextInt();
			hotel.setHotelid(hotelId);
			HotelRepository.viewHotel(hotel.getHotelid());
			System.out.println("are you sure you want to delete this hotel (y to confirm, n to cancle");
			String confirm = scanner.next();
			switch (confirm) {
			case "y":
				HotelRepository.deleteHotel(hotel);
				scanner.close();
				loopdeleteHotel = false;
				break;
			case "n":
				loopdeleteHotel = true;
				break;
			default:
				System.out.println("Invalid input!!!!");				
				loopdeleteHotel = true;				
				break;
			}
		} while (loopdeleteHotel == true);
	}
}
