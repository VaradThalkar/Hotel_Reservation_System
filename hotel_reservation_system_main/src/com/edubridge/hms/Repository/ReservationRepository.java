package com.edubridge.hms.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edubridge.hms.model.*;

public class ReservationRepository extends DBConfig {
	public static void createReservation(Reservation reservation) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into Reservation(UserId,HotelId,NoOfRooms,FromDate,ToDate,Status) values (?,?,?,?,?,?)");
		ps.setInt(1, reservation.getUserId());
		ps.setInt(2, reservation.getHotelId());
		ps.setInt(3, reservation.getNoOfRooms());
		ps.setString(4, reservation.getFromDate());
		ps.setString(5, reservation.getToDate());
		ps.setString(6, reservation.getStatus());
		// execute the query
		int i = ps.executeUpdate();
		if (i == 0) {
			System.out.println("inserting data failed");
		} else {
			System.out.println("Data insertion sucessful");
		}
	}

	public static void updateReservation(Reservation reservation) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement(
				"update Reservation set UserID =?,HotelId= ?,NoOfRooms =?,FromDate=?,toDate= ?,status=? where ReservationId=?;");
		ps.setInt(1, reservation.getUserId());
		ps.setInt(2, reservation.getHotelId());
		ps.setInt(3, reservation.getNoOfRooms());
		ps.setString(4, reservation.getFromDate());
		ps.setString(5, reservation.getToDate());
		ps.setString(6, reservation.getStatus());
		ps.setInt(7, reservation.getReservationId());
		// execute the query
		int i = ps.executeUpdate();

		if (i == 0) {
			System.out.println("Data updation failed");
		} else {
			System.out.println("Data updation sucessful");
			viewReservation(reservation.getReservationId());
		}
	}

	public static Reservation viewReservation(int ReservationId) throws SQLException {
		Connection con = DBConfig.openConnection();
		Statement statement = con.createStatement();
		Reservation reservation = new Reservation();
		String str = "Select * from Reservation where ReservationId=" + ReservationId + ";";
		ResultSet st = statement.executeQuery(str);
		while (st.next()) {
			reservation.setReservationId(st.getInt("ReservationId"));
			reservation.setUserId(st.getInt("UserId"));
			reservation.setHotelId(st.getInt("HotelId"));
			reservation.setNoOfRooms(st.getInt("NoOfRooms"));
			reservation.setFromDate(st.getString("FromDate"));
			reservation.setToDate(st.getString("toDate"));
			reservation.setStatus(st.getString("Status"));

		}
		return reservation;
	}

	public static List<Reservation> viewAllReservationByUserId(int userid) throws SQLException {
		Connection con = DBConfig.openConnection();
		Statement statement = con.createStatement();
		String str = "Select * from Reservation where UserId=" + userid + ";";
		List<Reservation> reservations = new ArrayList<>();
		ResultSet st = statement.executeQuery(str);
		while (st.next()) {
			Reservation reservation = new Reservation();
			reservation.setReservationId(st.getInt("ReservationId"));
			reservation.setUserId(st.getInt("UserId"));
			reservation.setHotelId(st.getInt("HotelId"));
			reservation.setNoOfRooms(st.getInt("NoOfRooms"));
			reservation.setFromDate(st.getString("FromDate"));
			reservation.setToDate(st.getString("ToDate"));
			reservation.setStatus(st.getString("Status"));
			reservations.add(reservation);
		}
		return reservations;
	}

	public static List<Reservation> viewAllReservation() throws SQLException {
		Connection con = DBConfig.openConnection();
		Statement statement = con.createStatement();
		String str = "Select * from Reservation;";
		List<Reservation> reservations = new ArrayList<>();
		ResultSet st = statement.executeQuery(str);
		while (st.next()) {
			Reservation reservation = new Reservation();
			reservation.setReservationId(st.getInt("ReservationId"));
			reservation.setUserId(st.getInt("UserId"));
			reservation.setHotelId(st.getInt("HotelId"));
			reservation.setNoOfRooms(st.getInt("NoOfRooms"));
			reservation.setFromDate(st.getString("FromDate"));
			reservation.setToDate(st.getString("ToDate"));
			reservation.setStatus(st.getString("Status"));
			reservations.add(reservation);
		}
		return reservations;
	}

	public static void deleteReservation(int ReservationId) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement("Delete from Reservation where ReservationId=?;");
		ps.setInt(1, ReservationId);
	}
}
