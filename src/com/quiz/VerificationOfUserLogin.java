package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerificationOfUserLogin {
	Connection connection = null;
	PreparedStatement ps2=null;
	boolean valueOfVerify;
	
	public boolean getUserLoginVerification(String username,String password) throws SQLException {
		try{
			// calling of connectionDetails method to establish connection
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection = connectionDetails.getConnection();
			// selection of username and password from student_registration table
			ps2=connection.prepareStatement("select username,password from students_registration");
//			ps2.setString(1,username);
//			ps2.setString(2, password);
			ResultSet rs2=ps2.executeQuery();
			
			// Store username and related password in map in key and value format
			Map<String,String> studentLoginData=new LinkedHashMap<String, String>();
			while(rs2.next()) {
				String username1=rs2.getString(1);
				String password2=rs2.getString(2);
//				System.out.println(username1);
				studentLoginData.put(username1,password2);
			}
			// now compare student username and password in database with user input
			for(Map.Entry<String,String> studentData:studentLoginData.entrySet()) {
				if(studentData.getKey().equals(username)&&studentData.getValue().equals(password)){
					valueOfVerify=true;
				}else{
				valueOfVerify=false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ps2.close();
		}
		return valueOfVerify;
	}
}
