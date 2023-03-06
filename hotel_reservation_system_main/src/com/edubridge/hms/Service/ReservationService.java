package com.edubridge.hms.Service;

import java.awt.font.NumericShaper;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.edubridge.hms.Repository.HotelRepository;
import com.edubridge.hms.Repository.ReservationRepository;
import com.edubridge.hms.Utilities.DateValidator;
import com.edubridge.hms.Utilities.DateValidatorUsingDateFormat;
import com.edubridge.hms.Utilities.IsValidName;
import com.edubridge.hms.Utilities.TableFormat;
import com.edubridge.hms.model.Hotel;
import com.edubridge.hms.model.Reservation;
import com.edubridge.hms.model.UserDetails;

public class ReservationService {
	public static UserDetails userDetails = new UserDetails();
	public static Reservation reservation = new Reservation();
	public static TableFormat tableFormat=new TableFormat();
	public static Hotel hotel = new Hotel();

	public static void createReservation(int userId) throws SQLException {
		boolean loopDate1 = false, loopDate2 = false, loopDate3 = false, loopNumberOfRooms = true;
		String status = "booked", fromDate, toDate;
		int hotelId, numberOfRooms;
		Hotel hotel1 = null;
		System.out.println("----------Enter user details----------");
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("Select an hotel from below:");
			List<Hotel> hotels = HotelRepository.viewAllHotel();
			tableFormat.HotelTable(hotels);
			System.out.print("Enter a Valid Hotel Id:");
			hotelId = scanner.nextInt();

			System.out.print("Enter Number of rooms you want (4 people per room):");
			numberOfRooms = scanner.nextInt();
			if (numberOfRooms > hotels.get(hotelId - 1).getAvailableRooms()) {
				System.out.println("requirement exceed the available rooms please try again!!");
			} else {
				loopNumberOfRooms = false;
				hotel1 = HotelRepository.viewHotel(hotelId);
			}
		} while (loopNumberOfRooms == true);
		do {
			System.out.print("Enter check-in date (yyyy-MM-dd):");
			fromDate = scanner.next();
			System.out.println();
			DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
			if (validator.isValid(fromDate) == true) {
				if (validator.currentDate(fromDate) == true) {
					loopDate1 = true;
				} else {
					loopDate1 = false;
				}
			} else {
				System.out.println("Enter a valid date");
				loopDate1 = false;
			}
		} while (loopDate1 == false);
		do {
			do {
				System.out.print("Enter check-out date (yyyy-MM-dd):");
				toDate = scanner.next();
				System.out.println();
				DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
				if (validator.isValid(toDate) == true) {
					if (validator.currentDate(toDate) == true) {
						loopDate2 = true;
					} else {
						loopDate2 = false;
					}
				} else {
					System.out.println("Enter a valid date");
					loopDate2 = false;
				}
			} while (loopDate2 == false);
			DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
			if (validator.compareDates(fromDate, toDate) == true) {
				loopDate3 = true;
			} else {
				loopDate3 = false;
			}
		} while (loopDate3 == false);
		reservation.setUserId(userId);
		reservation.setHotelId(hotelId);
		reservation.setNoOfRooms(numberOfRooms);
		reservation.setFromDate(fromDate);
		reservation.setToDate(toDate);
		reservation.setStatus(status);
		hotel1.setAvailableRooms(hotel1.getAvailableRooms() - numberOfRooms);
		ReservationRepository.createReservation(reservation);
		HotelRepository.updateHotel(hotel1);
	}

	public static void updateReservationByUserId(int userId) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		int reservationId, numberOfRooms;
		String fromDate = null, toDate = null;
		Reservation reservation1 = null;
		Hotel hotel1 = null;

		boolean loopUpdate = true, loopReservationId = true, loopNumberOfRooms = true, loopDate1 = false,
				loopDate2 = false, loopDate3 = false;
		System.out.println("----------Update Reservation Details----------");

		List<Reservation> reservations = ReservationRepository.viewAllReservationByUserId(userId);
		tableFormat.ReservationTable(reservations);
		do {
			System.out.println("Please enter a valid Reservation Id to update:");
			reservationId = scanner.nextInt();
			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getReservationId() == reservationId) {
					reservation1 = ReservationRepository.viewReservation(reservationId);
					loopReservationId = false;
				} else {
					loopReservationId = true;
				}
			}
		} while (loopReservationId == true);

		do {
			System.out.println("1.number of rooms");
			System.out.println("2.check in and check out Date");
			System.out.println("Please select a valid input");
			int choice = scanner.nextInt();
			hotel1 = HotelRepository.viewHotel(reservation1.getHotelId());

			switch (choice) {
			case 1:
				do {
					System.out.println("Enter New number of rooms");
					numberOfRooms = scanner.nextInt();
					if (numberOfRooms > 0) {
						if (numberOfRooms < reservation1.getNoOfRooms()) {
							hotel1.setAvailableRooms(
									hotel1.getAvailableRooms() + (reservation1.getNoOfRooms() - numberOfRooms));
							reservation1.setNoOfRooms(numberOfRooms);
							loopNumberOfRooms = false;
						} else if (numberOfRooms > reservation1.getNoOfRooms()) {

							if (numberOfRooms > hotel1.getAvailableRooms()) {
								System.out.println("requirement exceed the available rooms please try again!!");
							} else {
								hotel1.setAvailableRooms(hotel1.getAvailableRooms() - numberOfRooms);
								reservation1.setNoOfRooms(numberOfRooms);
								loopNumberOfRooms = false;
							}
						}
					}
				} while (loopNumberOfRooms == true);
				ReservationRepository.updateReservation(reservation1);
				HotelRepository.updateHotel(hotel1);
				loopUpdate=false;
				break;
			case 2:
				do {
					System.out.print("Enter check-in date (yyyy-MM-dd):");
					fromDate = scanner.next();
					System.out.println();
					DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
					if (validator.isValid(fromDate) == true) {
						if (validator.currentDate(fromDate) == true) {
							loopDate1 = true;
						} else {
							loopDate1 = false;
						}
					} else {
						System.out.println("Enter a valid date");
						loopDate1 = false;
					}
				} while (loopDate1 == false);
				do {
					do {
						System.out.print("Enter check-out date (yyyy-MM-dd):");
						toDate = scanner.next();
						System.out.println();
						DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
						if (validator.isValid(toDate) == true) {
							if (validator.currentDate(toDate) == true) {
								loopDate2 = true;
							} else {
								loopDate2 = false;
							}
						} else {
							System.out.println("Enter a valid date");
							loopDate2 = false;
						}
					} while (loopDate2 == false);
					DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
					if (validator.compareDates(fromDate, toDate) == true) {
						loopDate3 = true;
					} else {
						loopDate3 = false;
					}
				} while (loopDate3 == false);
				reservation1.setFromDate(fromDate);
				reservation1.setToDate(toDate);
				ReservationRepository.updateReservation(reservation1);
				loopUpdate=false;
				break;
			default:
				System.out.println("Invalid input");
				loopUpdate=true;
			}
		} while (loopUpdate == true);

	}

	public static void updateReservation() throws SQLException {
		System.out.println("----------Update Reservation Details----------");
		Scanner scanner = new Scanner(System.in);
		int reservationId, numberOfRooms;
		String fromDate = null, toDate = null;
		Reservation reservation1 = null;
		Hotel hotel1 = null;

		boolean loopUpdate = true, loopReservationId = true, loopNumberOfRooms = true, loopDate1 = false,
				loopDate2 = false, loopDate3 = false;
		System.out.println("----------Update Reservation Details----------");

		List<Reservation> reservations = ReservationRepository.viewAllReservation();
		tableFormat.ReservationTable(reservations);
		do {
			System.out.println("Please enter a valid Reservation Id to update:");
			reservationId = scanner.nextInt();
			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getReservationId() == reservationId) {
					reservation1 = ReservationRepository.viewReservation(reservationId);
					loopReservationId = false;
				} else {
					loopReservationId = true;
				}
			}
		} while (loopReservationId == true);

		do {
			System.out.println("1.number of rooms");
			System.out.println("2.check in and check out Date");
			System.out.println("Please select a valid input");
			int choice = scanner.nextInt();
			hotel1 = HotelRepository.viewHotel(reservation1.getHotelId());

			switch (choice) {
			case 1:
				do {
					System.out.println("Enter New number of rooms");
					numberOfRooms = scanner.nextInt();
					if (numberOfRooms > 0) {
						if (numberOfRooms < reservation1.getNoOfRooms()) {
							hotel1.setAvailableRooms(
									hotel1.getAvailableRooms() + (reservation1.getNoOfRooms() - numberOfRooms));
							reservation1.setNoOfRooms(numberOfRooms);
							loopNumberOfRooms = false;
						} else if (numberOfRooms > reservation1.getNoOfRooms()) {

							if (numberOfRooms > hotel1.getAvailableRooms()) {
								System.out.println("requirement exceed the available rooms please try again!!");
							} else {
								hotel1.setAvailableRooms(hotel1.getAvailableRooms() - numberOfRooms);
								reservation1.setNoOfRooms(numberOfRooms);
								loopNumberOfRooms = false;
							}
						}
					}
				} while (loopNumberOfRooms == true);
				ReservationRepository.updateReservation(reservation1);
				HotelRepository.updateHotel(hotel1);
				loopUpdate=false;
				break;
			case 2:
				do {
					System.out.print("Enter check-in date (yyyy-MM-dd):");
					fromDate = scanner.next();
					System.out.println();
					DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
					if (validator.isValid(fromDate) == true) {
						if (validator.currentDate(fromDate) == true) {
							loopDate1 = true;
						} else {
							loopDate1 = false;
						}
					} else {
						System.out.println("Enter a valid date");
						loopDate1 = false;
					}
				} while (loopDate1 == false);
				do {
					do {
						System.out.print("Enter check-out date (yyyy-MM-dd):");
						toDate = scanner.next();
						System.out.println();
						DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
						if (validator.isValid(toDate) == true) {
							if (validator.currentDate(toDate) == true) {
								loopDate2 = true;
							} else {
								loopDate2 = false;
							}
						} else {
							System.out.println("Enter a valid date");
							loopDate2 = false;
						}
					} while (loopDate2 == false);
					DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
					if (validator.compareDates(fromDate, toDate) == true) {
						loopDate3 = true;
					} else {
						loopDate3 = false;
					}
				} while (loopDate3 == false);
				reservation1.setFromDate(fromDate);
				reservation1.setToDate(toDate);
				ReservationRepository.updateReservation(reservation1);
				loopUpdate=false;
				break;
			default:
				System.out.println("Invalid input");
				loopUpdate=true;
			}
		} while (loopUpdate == true);

	}

	public static void viewReservationByUserId(int userId) throws SQLException {
		System.out.println("----------All Reservations----------");
		List<Reservation> reservations = ReservationRepository.viewAllReservationByUserId(userId);
		tableFormat.ReservationTable(reservations);
		System.out.println("Press any key to continue");
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
	}

	public static void viewAllReservation() throws SQLException {
		System.out.println("----------All Reservations----------");
		List<Reservation> reservations = ReservationRepository.viewAllReservation();
		tableFormat.ReservationTable(reservations);
		System.out.println("Press any key to continue");
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
	}

	public static void deleteReservation() throws SQLException {
		System.out.println("----------Delete Reservation----------");
		int reservationId;
		boolean loopReservationId=true;
		Scanner scanner=new Scanner(System.in);
		List<Reservation> reservations = ReservationRepository.viewAllReservation();
		tableFormat.ReservationTable(reservations);
		do {
			System.out.println("Please enter a valid Reservation Id to Delete:");
			reservationId = scanner.nextInt();
			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getReservationId() == reservationId) {
					ReservationRepository.deleteReservation(reservationId);
					loopReservationId = false;
				} else {
					loopReservationId = true;
				}
			}
		} while (loopReservationId == true);
	}

	public static void cancelReservation(int userId) throws SQLException {
		System.out.println("----------Cancel Reservation----------");
		int reservationId;
		Scanner scanner=new Scanner(System.in);
		Reservation reservation1 = null;
		boolean loopReservationId=true;
		List<Reservation> reservations = ReservationRepository.viewAllReservationByUserId(userId);
		tableFormat.ReservationTable(reservations);
		do {
			System.out.println("Please enter a valid Reservation Id to update:");
			reservationId = scanner.nextInt();
			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getReservationId() == reservationId) {
					reservation1 = ReservationRepository.viewReservation(reservationId);
					loopReservationId = false;
				} else {
					loopReservationId = true;
				}
			}
		} while (loopReservationId == true);
		System.out.println("confirm cancellation.....(y to confirm/ n to cancel");
		String confirm=scanner.next();
		switch(confirm) {
		case "y":
			System.out.println("updating status....");
			reservation1.setStatus("Cancelled");
			ReservationRepository.updateReservation(reservation1);
			break;
		case "n":
			
			break;
		default:
			System.out.println("Invalid input!!!!");
		}
	}
}
