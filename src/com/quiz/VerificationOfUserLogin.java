package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerificationOfUserLogin {
	/*getUserLoginVerification()-method is used to verify whether
 User is Registered student(in student_data table)*/
	/*Author Name-Pranoti*/	
	public static boolean getUserLoginVerification(String username,String password) throws SQLException {

		Connection connection = null;
		PreparedStatement ps2=null;
		boolean valueOfVerify=false;
		try{

			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection = connectionDetails.getConnection();
			ps2=connection.prepareStatement("select username,password from student_data");
			ResultSet rs2=ps2.executeQuery();
			Map<String,String> studentLoginData=new LinkedHashMap<String, String>();
			while(rs2.next()) {
				String username1=rs2.getString(1);
				String password2=rs2.getString(2);
				studentLoginData.put(username1,password2);
			}
			for(Map.Entry<String,String> studentData:studentLoginData.entrySet()) {
				if(studentData.getKey().equals(username)&&studentData.getValue().equals(password)){
					valueOfVerify=true;
				}
			}

			if(valueOfVerify==false) {
				System.out.println("Username or Password is incorrect.\n");
			}


		}catch(Exception e) {
			System.out.println("Unexpected Error...");
			System.out.println(e.getMessage());
		}finally {
			connection.close();
			ps2.close();
		}
		return valueOfVerify;
	}
}
