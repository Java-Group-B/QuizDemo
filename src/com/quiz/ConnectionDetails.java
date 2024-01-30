package com.quiz;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDetails {
	public  Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "Shaikh@199786");
		return connection;
	}
}
