package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
public class AdminCredentials {
/*getAdminVerification()-method is used to verify admin credentials*/
/*Author Name-Priyanka*/	
	public boolean getAdminVerification(String uname,String pword) throws Exception {
		boolean flag=false;
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {

			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			preparedStatement = connection.prepareStatement("select username,password from admin_credentials;");
			ResultSet resultSet=preparedStatement.executeQuery();
			Map<String,String> credentials=new LinkedHashMap<String, String>();
			while(resultSet.next()) {
				credentials.put(resultSet.getString(1),resultSet.getString(2));
			}
			for(Map.Entry<String,String> m:credentials.entrySet()) {
				if(m.getKey().equals(uname)&&m.getValue().equals(pword)){
					flag=true;
				}
			}
		}
		catch (Exception e) {
			System.out.println("Unexpected Error...");
			System.out.println(e.getMessage());
		}
		finally {
			connection.close();
			preparedStatement.close();
		}	
		return flag;
	}
}
