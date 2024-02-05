package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ImplDisplayResult implements DisplayResult{
	/*getDisplayResult() method is used to verify user details.*/
	/*Author Name-Irfan*/
	@Override
	public void getDisplayResult() throws Exception {
		System.out.println("Enter the username :");
		String uname=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String pword=QuizDemo.scanner.next();
		boolean verifyUser=VerificationOfUserLogin.getUserLoginVerification(uname, pword);
		if(verifyUser==true) {		
			boolean varify=VerificationOfLoginDataForResult.getUserVarification(uname,pword);
			if(varify==true) {
				getResult(uname,pword);	
			}
			else{
				try {
					throw new QuizNotAttemptedException("You have not attempted Quiz.");
				}
				catch(QuizNotAttemptedException q) {
					System.out.println(q.getMessage());
				}
			}
		}
	}

	/*getResult() method is used to display result of student, after verification of user/student.*/
	/*Author Name- Irfan*/
	public static void getResult(String uname,String pword) throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			System.out.println("-------Result Page-------");
			preparedStatement = connection.prepareStatement("select student_result.score from student_data inner join student_result on student_data.username=student_result.username where student_result.username=?;");
			preparedStatement.setString(1,uname);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("Your score is "+resultSet.getString(1));
			}
		}
		catch (Exception e) {
			System.out.println("Unexpected Error...");
		}
		finally {

			connection.close();
			preparedStatement.close();
		}
	}
}
