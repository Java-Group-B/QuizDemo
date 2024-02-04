package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ImplDisplayResult implements DisplayResult{

	@Override
	public void getDisplayResult() throws Exception {
		/*take username and password from user*/
		System.out.println("Enter the username :");
		String uname=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String pword=QuizDemo.scanner.next();
		boolean verifyUser=VerificationOfUserLogin.getUserLoginVerification(uname, pword);
		/*call getUserVarification method and verify user*/
				if(verifyUser==true) {		
		boolean varify=VerificationOfLoginDataForResult.getUserVarification(uname,pword);
		//if credentials are correct, then call getResult to display result 
		if(varify==true) {
			getResult(uname,pword);	
		}
//		try {
		else{
			try {
				/*throw exception if credentials not match*/
				throw new QuizNotAttemptedException("You have not attempted Quiz.");
			}
			catch(QuizNotAttemptedException q) {
				System.out.println(q.getMessage());
			}
				}
		}
	}
	public static void getResult(String uname,String pword) throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			/*Pass the sql query via preparedstatement and fetch score of student*/
			preparedStatement = connection.prepareStatement("select student_result.score from student_data inner join student_result on student_data.username=student_result.username where student_result.username=?;");
			preparedStatement.setString(1,uname);
			ResultSet resultSet=preparedStatement.executeQuery();

			while(resultSet.next()) {
				System.out.println("Your score is "+resultSet.getString(1));
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
