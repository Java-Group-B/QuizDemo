package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;


public class ImplDisplayResult implements DisplayResult{

	@Override
	public void getDisplayResult() throws Exception {
		/*take username and password from user*/
		System.out.println("Enter the username :");
		String uname=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String pword=QuizDemo.scanner.next();
		/*call getUserVarification method and verify user*/
		boolean varify=VerificationOfUserLoginData.getUserVarification(uname,pword);
		//if credentials are correct, then call getResult to display result 
		if(varify==true) {
			getResult(uname,pword);	
		}
	}
	
	public static void getResult(String uname,String pword) throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			/*Pass the sql query via preparedstatement and fetch score of student*/
			preparedStatement = connection.prepareStatement("select student.username,student.password,scoredetails.score from student inner join scoredetails on student.username=scoredetails.username where scoredetails.username=?;");
			preparedStatement.setString(1,uname);
			ResultSet resultSet=preparedStatement.executeQuery();

			while(resultSet.next()) {
				System.out.print("Your score is "+resultSet.getInt(3));
			}
		}
		catch (Exception e) {
           /*Even after verification, if score is not fetching properly
             then it may be some unexpected error*/
            System.out.println("Unexpected Error...");
		}
		finally {
			connection.close();
			preparedStatement.close();
		}
	}
}
