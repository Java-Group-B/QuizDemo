package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerificationOfLoginDataForResult {

	public static boolean getUserVarification(String uname,String pword) throws SQLException {
		/*set one flag, if user credentials are matched then it
	    i== true and if not  then i==false(also throw exception)
		 */
		boolean i=false;
		/*establish connection between java application & database*/
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {

			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			/*pass sql query through preparedStatement, to fetch username & password from database*/
			preparedStatement = connection.prepareStatement("select student_result.username,student_data.password from student_data inner join student_result on student_data.username=student_result.username;");
			ResultSet resultSet=preparedStatement.executeQuery();
			/*Store username and corresponding password in map in form of key,value*/
			Map<String,String> credentials=new LinkedHashMap<String, String>();
			while(resultSet.next()) {
				credentials.put(resultSet.getString(1),resultSet.getString(2));
			}
			/*now compare given credentials with database,if matched then i==true,else i==false*/
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
