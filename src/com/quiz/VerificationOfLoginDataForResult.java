package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerificationOfLoginDataForResult {
	/*getUserLoginVerification()-method is used to verify whether
	 User is attempted Quiz or not( in student_result table)*/
	/*Author Name-Irfan*/	
	public static boolean getUserVarification(String uname,String pword) throws SQLException {
		boolean i=false;
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {

			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			preparedStatement = connection.prepareStatement("select student_result.username,student_data.password from student_data inner join student_result on student_data.username=student_result.username;");
			ResultSet resultSet=preparedStatement.executeQuery();
			Map<String,String> credentials=new LinkedHashMap<String, String>();
			while(resultSet.next()) {
				credentials.put(resultSet.getString(1),resultSet.getString(2));
			}
			for(Map.Entry<String,String> m:credentials.entrySet()) {
				if(m.getKey().equals(uname)&&m.getValue().equals(pword)){
					i=true;
				}}
		}
		catch (Exception e) {
			System.out.println("Unexpected Error...");
			System.out.println(e.getMessage());
		}
		finally {
			connection.clearWarnings();
			connection.close();
			preparedStatement.close();
		}	
		return i;
	}
}
