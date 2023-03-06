package com.edubridge.hms.Utilities;

import java.sql.SQLException;
import java.util.List;

import com.edubridge.hms.Repository.HotelRepository;
import com.edubridge.hms.Repository.UserRepository;
import com.edubridge.hms.model.Hotel;
import com.edubridge.hms.model.Reservation;
import com.edubridge.hms.model.UserDetails;

public class TableFormat {
	public static void HotelTable(List<Hotel> hotel) {
		String leftAlignFormat = "| %-9d | %-15s | %-16d | %-16d |%n";
		System.out.format(
				"+-----------+-----------------+------------------+------------------+%n");
		System.out.format(
				"| HotelId   | HotelName       | NumberOfRooms    | AvailableRooms   |%n");
		System.out.format(
				"+-----------+-----------------+------------------+------------------+%n");
		int size=hotel.size();
		for (int i=0;i<size;i++) {
			int hotelId=hotel.get(i).getHotelid();
			String hotelName=hotel.get(i).getHotelName();
			int numberOfRooms=hotel.get(i).getNumberOfRooms();
			int availableRooms=hotel.get(i).getAvailableRooms();
			System.out.format(leftAlignFormat, hotelId, hotelName, numberOfRooms, availableRooms);
			System.out.format(
				"+-----------+-----------------+------------------+----------------------+%n");
		}
	}
	public static void ReservationTable(List<Reservation> reservation) throws SQLException {
		String leftAlignFormat = "| %-14d | %-14s | %-15s | %-14d | %-14d | %-14s | %-14s | %-15s |%n";
		System.out.format(
				"+----------------+----------------+-----------------+----------------+----------------+----------------+-----------------+%n");
		System.out.format(
				"| ReservaionId   | UserName       | HotelName       | NumberOfRooms  | Check-In Date  | Check-out Date | status          |%n");
		System.out.format(
				"+----------------+----------------+-----------------+----------------+----------------+----------------+-----------------+%n");
		int size=reservation.size();
		for (int i=0;i<size;i++) {
			UserDetails userDetails=UserRepository.viewUser(reservation.get(i).getUserId());
			Hotel hotel=HotelRepository.viewHotel(reservation.get(i).getHotelId());
			int reservationId=reservation.get(i).getReservationId();
			String userName=userDetails.getUserName();
			String hotelName=hotel.getHotelName();
			int numberOfRooms=reservation.get(i).getNoOfRooms();
			String fromDate=reservation.get(i).getFromDate();
			String toDate=reservation.get(i).getToDate();
			String status=reservation.get(i).getStatus();
			System.out.format(leftAlignFormat, reservationId, userName, hotelName, numberOfRooms, fromDate, toDate, status);
			System.out.format(
				"+----------------+----------------+-----------------+----------------+----------------+----------------+-----------------+%n");
		}
	}
	public static void UserDetailsTable(List<UserDetails> userDetails) {
		String leftAlignFormat = "| %-9d | %-15s | %-15s | %-16d | %-16d |%n";
		System.out.format(
				"+-----------+-----------------+-----------------+------------------+------------------+%n");
		System.out.format(
				"| UserId    | UserName        | Name            | Contact Number   | Aadhar Number    |%n");
		System.out.format(
				"+-----------+-----------------+-----------------+------------------+------------------+%n");
		int size=userDetails.size();
		for (int i=0;i<size;i++) {
			int userId=userDetails.get(i).getUserId();
			String userName=userDetails.get(i).getUserName();
			String name=userDetails.get(i).getName();
			long contactNumber=userDetails.get(i).getContactNumber();
			long aadharNumber=userDetails.get(i).getAdhaarNumber();
			System.out.format(leftAlignFormat, userId, userName, name, contactNumber, aadharNumber);
			System.out.format(
				"+-----------+-----------------+------------------+----------------------+------------------+%n");
		}
	}
}
