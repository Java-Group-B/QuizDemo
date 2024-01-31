package com.quiz;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDetails {
	public  Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mn12", "root", "root");
		return connection;
	}
}
