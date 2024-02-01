package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerificationOfUserLoginData {

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
			preparedStatement = connection.prepareStatement("select student.username,student.password from student inner join scoredetails on student.username=scoredetails.username;");
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
			if(i==false) {
				/*throw exception if credentials not match*/
				throw new Exception();
			}
		}
		catch (Exception e) {
			System.out.println("Username Or Password is incorrect\nOr You may not attempted exam.");
		}
		finally {
			connection.close();
			preparedStatement.close();
		}	
		return i;
	}
}
