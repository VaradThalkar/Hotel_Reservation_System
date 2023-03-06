package com.edubridge.hms.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConfig {
	public static Connection openConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "admin");
		Statement statement = con.createStatement();
		return con;

	}

	public void closeConnection(Connection con) throws SQLException {
		con.close();
	}
}
