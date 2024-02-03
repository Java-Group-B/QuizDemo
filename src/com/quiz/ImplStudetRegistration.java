package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ImplStudetRegistration implements StudentRegistration{

	@Override
	public void getStudentRegistration() throws Exception {

		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			preparedStatement = connection.prepareStatement("insert into student_data(firstname,lastname,username,password,city,emailid,mobilenumber) values (?,?,?,?,?,?,?)");
			System.out.println("Enter First Name -");
			preparedStatement.setString(1, QuizDemo.scanner.next());
			System.out.println("Enter Last Name -");
			preparedStatement.setString(2, QuizDemo.scanner.next());
			System.out.println("Enter Username -");
		//	String name=QuizDemo.scanner.next();
			//Example ex=new Example();
			//ex.method(name);
			preparedStatement.setString(3, QuizDemo.scanner.next());
			System.out.println("Enter Password -");
			preparedStatement.setString(4, QuizDemo.scanner.next());
			System.out.println("Enter City -");
			preparedStatement.setString(5, QuizDemo.scanner.next());
			System.out.println("Enter e-mail-id -");
			preparedStatement.setString(6, QuizDemo.scanner.next());
			System.out.println("Enter Mobile-Number -");
			preparedStatement.setString(7, QuizDemo.scanner.next());
			int i=preparedStatement.executeUpdate();
			if(i==1) {
				System.out.println("Registeration done successfully...");
				System.out.println("\nDo you want to Login Now?(Press \"y\" for yes,and press any other key to exit.");
				String user_choice=QuizDemo.scanner.next();
				if(user_choice.equalsIgnoreCase("y")) {
				StudentLogin studentLogin=new ImplStudentLogin();
				studentLogin.getStudentLogin();
				}
				
			}
			else {
				System.out.println("Invalid Data");
			}

		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			System.out.println("\n Username or Mail-id or Mobile-Number already exist.");
		}
		finally {
			connection.close();
			preparedStatement.close();

		}	
	}

}
