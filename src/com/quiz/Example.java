package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Example {
	Connection connection=null;
	PreparedStatement ps=null;
	String admin_username=null;
	public void method(String user_name) throws Exception {
		
		try{
			// calling of connectionDetails method to establish connection
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection = connectionDetails.getConnection();
			ps=connection.prepareStatement("select admin_username from admin_credentials where id=1");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			admin_username=rs.getString(1);
			}
			if(user_name.equalsIgnoreCase(admin_username)) {
				System.out.println("not accepted");
			}else {
				System.out.println("accepted");
			}
	}catch(SQLException sqle) {
		sqle.printStackTrace();
	}
  }
}
