package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerificationOfUsernameForAdmin {
	/*setVerificationOfUsername()-method is used to verify user-registered or not in student data*/
	/*Author Nam-Pranoti*/	
	public static boolean setVerificationOfUsername(String usernameOfStudent) throws SQLException {

		Connection connection=null;
		PreparedStatement ps =null;
		boolean verify = false;

		try {
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			ps=connection.prepareStatement("select username from student_result");
			ResultSet rs=ps.executeQuery();
			List<String> arrayOfUsername=new ArrayList<String>();
			while(rs.next()) {
				arrayOfUsername.add(rs.getString(1));
			}
			for(String username2:arrayOfUsername) {
				if(usernameOfStudent.equals(username2)){
					verify=true;
					break;
				}else {		
					verify=false;
					continue;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			ps.close();
		}
		return verify;	
	}
}