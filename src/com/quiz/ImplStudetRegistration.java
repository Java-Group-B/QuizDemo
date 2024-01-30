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
			preparedStatement = connection.prepareStatement("insert into students(firstname,lastname,username,password,city,mail_id,mobile_no) values (?,?,?,?,?,?,?)");
			System.out.println("Enter First Name -");
			preparedStatement.setString(1, QuizDemo.scanner.next());
			System.out.println("Enter Last Name -");
			preparedStatement.setString(2, QuizDemo.scanner.next());
			System.out.println("Enter Username -");
			preparedStatement.setString(3, QuizDemo.scanner.next());
			System.out.println("Enter Password -");
			preparedStatement.setString(4, QuizDemo.scanner.next());
			System.out.println("Enter City -");
			preparedStatement.setString(5, QuizDemo.scanner.next());
			System.out.println("Enter Mail-id -");
			preparedStatement.setString(6, QuizDemo.scanner.next());
			System.out.println("Enter Mobile-Number -");
			preparedStatement.setString(7, QuizDemo.scanner.next());
			int i=preparedStatement.executeUpdate();
			if(i==1) {
				System.out.println("Registeration done successfully...");	
			}
			else {
				System.out.println("Invalid Data");
			}

		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			System.out.println("\nDuplicate values for Username,Mail-id,Mobile-Number are not allowed.");
		}
		finally {
			connection.close();
			preparedStatement.close();

		}	
	}

}
