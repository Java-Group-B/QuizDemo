package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsUsernameValid {
	
	public static boolean isValidUsername(String username)throws Exception {
		Connection connection=null;
		PreparedStatement ps=null;
		String admin_username=null;
		boolean i=false;
		try{
			// calling of connectionDetails method to establish connection
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection = connectionDetails.getConnection();
			ps=connection.prepareStatement("select username from admin_credentials");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			admin_username=rs.getString(1);
			}
			if(username.equalsIgnoreCase(admin_username)) {
				System.out.println("Username already exist\nPlease enter another username -");
				i=true;
			}
			
	}catch(SQLException sqle) {
		System.out.println(sqle.getMessage());
	}
		return i;
  }
}
