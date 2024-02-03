package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
public class ImplAddQuestion implements AddQuestion{

	@Override
	public void getAddQuestion() throws Exception {
		/*take username and password from user*/
		System.out.println("Enter the username :");
		String uname=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String pword=QuizDemo.scanner.next();
		
		/*call getUserVarification method and verify user*/
		
		
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		//if credentials are correct, then call addNewQuestion method
		if(varify==true) {
			addNewQuestion();	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);
			
			try{
				if(isNotStudent==true) {
			
				throw new AccessDeniedForResultException("Access Denied for students...");
		}}
			catch (AccessDeniedForResultException e) {
				System.out.println(e.getMessage());
			}
	}
		}
	private static void addNewQuestion() throws Exception {

		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			System.out.println("Enter the Question :");
			QuizDemo.scanner.nextLine();
			preparedStatement = connection.prepareStatement("insert into question_bank(question,option_a,option_b,option_c,option_d,correct_answer)values (?,?,?,?,?,?)");
			String question=QuizDemo.scanner.nextLine();
			preparedStatement.setString(1, question);
			System.out.println("Enter option - a :");
			String option_a=QuizDemo.scanner.nextLine();
			preparedStatement.setString(2, option_a);
			System.out.println("Enter option - b :");
			String option_b=QuizDemo.scanner.nextLine();
			preparedStatement.setString(3, option_b);
			System.out.println("Enter option - c :");
			String option_c=QuizDemo.scanner.nextLine();
			preparedStatement.setString(4, option_c);
			System.out.println("Enter option - d :");
			String option_d=QuizDemo.scanner.nextLine();
			preparedStatement.setString(5, option_d);
			System.out.println("Enter correct answer :");
			String correct_answer=QuizDemo.scanner.nextLine();
			preparedStatement.setString(6, correct_answer);
			preparedStatement.executeUpdate();
			System.out.println("Question added to Question Bank Successfully...");
		}
		catch (Exception e) {
			System.out.println("Unexpected Error...");
			System.out.println(e.getMessage());
		}
		finally {
			connection.close();
			preparedStatement.close();
		}

	}
}
