package com.quiz;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDetails {
/*getConnection()-method is used to
 establish connection between java application and MySql Database*/
/*Author Name- Himanshu*/	
	public  Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/group_b_db", "root", "root");
		return connection;
	}
}
