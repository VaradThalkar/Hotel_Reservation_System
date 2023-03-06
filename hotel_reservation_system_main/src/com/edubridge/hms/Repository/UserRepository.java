package com.edubridge.hms.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.edubridge.hms.model.*;

public class UserRepository extends DBConfig {

	public static UserDetails createUser(UserDetails userDetails) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement("insert into userDetails(UserName,Password,Name,ContactNumber,AdhaarNumber) values(?,?,?,?,?)");
		ps.setString(1, userDetails.getUserName());
		ps.setString(2, userDetails.getPassword());
		ps.setString(3, userDetails.getName());
		ps.setLong(4, userDetails.getContactNumber());
		ps.setLong(5, userDetails.getAdhaarNumber());
		// execute the query
		int i = ps.executeUpdate();
		if (i == 0) {
			System.out.println("inserting data failed");
		} else {
			System.out.println("Data insertion sucessful");
		}
		UserDetails user=viewUserByUserName(userDetails.getUserName());
		return user;
	}

	public static void updateUser(UserDetails userDetails) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement(
				"update userdetails set UserName =?,Password = ?,Name =?,ContactNumber=?,AdhaarNumber= ? where UserId=?;");
		ps.setString(1, userDetails.getUserName());
		ps.setString(2, userDetails.getPassword());
		ps.setString(3, userDetails.getName());
		ps.setLong(4, userDetails.getContactNumber());
		ps.setLong(5, userDetails.getAdhaarNumber());
		ps.setInt(6, userDetails.getUserId());
		// execute the query
		int i = ps.executeUpdate();
		
		if (i == 0) {
			System.out.println("Data updation failed");
		} else {
			System.out.println("Data updation sucessful");
			viewUserByUserName(userDetails.getUserName()); 
		}
	}

	public static UserDetails viewUser(int i) throws SQLException {
		UserDetails userDetails=new UserDetails();
		Connection con = DBConfig.openConnection();
		Statement statement = con.createStatement();
		String str = "Select * from UserDetails where userId="+i+";";
		ResultSet st = statement.executeQuery(str);
		while (st.next()) {
			userDetails.setUserId(st.getInt("UserId"));
			userDetails.setUserName(st.getString("UserName"));
			userDetails.setPassword(st.getString("Password"));
			userDetails.setName(st.getString("Name"));
			userDetails.setContactNumber(st.getLong("ContactNumber"));
			userDetails.setAdhaarNumber(st.getLong("AdhaarNumber"));
		}
		return userDetails;

	}

	public static UserDetails viewUserByUserName(String userName) throws SQLException {
		UserDetails userDetails=new UserDetails();
		Connection con = DBConfig.openConnection();
		Statement statement = con.createStatement();
		String str = "Select * from UserDetails where UserName="+userName+";";
		ResultSet st = statement.executeQuery(str);
		while (st.next()) {
			userDetails.setUserId(st.getInt("UserId"));
			userDetails.setUserName(st.getString("UserName"));
			userDetails.setPassword(st.getString("Password"));
			userDetails.setName(st.getString("Name"));
			userDetails.setContactNumber(st.getLong("ContactNumber"));
			userDetails.setAdhaarNumber(st.getLong("AdhaarNumber"));
		}
		return userDetails;

	}
	
	public static void deleteUser(UserDetails userDetails) throws SQLException {
		Connection con = DBConfig.openConnection();
		PreparedStatement ps = con.prepareStatement("Delete from UserDetails where UserId=?;");
		ps.setInt(1, userDetails.getUserId());
	}
}
