package com.edubridge.hms.Repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edubridge.hms.model.*;


public class HotelRepository extends DBConfig {
	public static void createHotel(Hotel hotel) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement("insert into Hotel(HotelName,NumberOfRooms,AvailableRooms) values (?,?,?)");
		ps.setString(1, hotel.getHotelName());
		ps.setInt(2, hotel.getNumberOfRooms());
		ps.setInt(3, hotel.getAvailableRooms());
		// execute the query
		int i = ps.executeUpdate();
		if (i == 0) {
			System.out.println("inserting data failed");
		} else {
			System.out.println("Data insertion sucessful");
		}
	}

	public static void updateHotel(Hotel hotel) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement("update Hotel set HotelName =?,NumberOfRooms=?,AvailableRooms= ? where HotelId=?;");
		ps.setString(1, hotel.getHotelName());
		ps.setInt(2, hotel.getNumberOfRooms());
		ps.setInt(3, hotel.getAvailableRooms());
		ps.setInt(4, hotel.getHotelid());
		// execute the query
		int i = ps.executeUpdate();
		if (i == 0) {
			System.out.println("Data updation failed");
		} else {
			System.out.println("Data updation sucessful");
		}
	}

	public static Hotel viewHotel(int hotelId) throws SQLException {
		Connection con = DBConfig.openConnection();
		Statement statement = con.createStatement();
		Hotel hotel = new Hotel();
		String str = "Select * from Hotel where hotelId="+hotelId+";";
		ResultSet st = statement.executeQuery(str);
		while(st.next()) {
			hotel.setHotelid(st.getInt("HotelId"));
			hotel.setHotelName(st.getString("HotelName"));
			hotel.setNumberOfRooms(st.getInt("NumberOfRooms"));
			hotel.setAvailableRooms(st.getInt("AvailableRooms"));
		}
		return hotel;
	}
	
	public static List<Hotel> viewAllHotel() throws SQLException {
		Connection con = DBConfig.openConnection();
		Statement statement = con.createStatement();
		String str = "Select * from Hotel;";
		List<Hotel> hotels=new ArrayList<>();
		ResultSet st = statement.executeQuery(str);
		while(st.next()) {
			Hotel hotel=new Hotel();
			hotel.setHotelid(st.getInt("HotelId"));
			hotel.setHotelName(st.getString("HotelName"));
			hotel.setNumberOfRooms(st.getInt("NumberOfRooms"));
			hotel.setAvailableRooms(st.getInt("AvailableRooms"));
			hotels.add(hotel);
		}
		return hotels;
	}
	
	public static void deleteHotel(Hotel hotel) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement("Delete from Hotel where HotelId=?;");
		ps.setInt(1, hotel.getHotelid());
		viewHotel(hotel.getHotelid());
	}
}
