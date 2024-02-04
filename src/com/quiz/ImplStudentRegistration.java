package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ImplStudentRegistration implements StudentRegistration{

	boolean i=false;
	String firstname;
	String lastname;
	String username;
	String password;
	String city;
	String emailId;
	String mobileNumber;
	
	@Override
	public void getStudentRegistration() throws Exception {

		System.out.println("------------ Student Registration ------------");
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		try {
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnection();
			preparedStatement = connection.prepareStatement("insert into student_data(firstname,lastname,username,password,city,emailid,mobilenumber) values (?,?,?,?,?,?,?)");
				Student student=new Student();
				
			System.out.println("Enter First Name -");
			do {
				QuizDemo.scanner=new Scanner(System.in);
				firstname=QuizDemo.scanner.next();
				i=student.setFirstname(firstname);
			}while(i==true);
			
			System.out.println("Enter Last Name -");
			do {
				QuizDemo.scanner=new Scanner(System.in);
				lastname=QuizDemo.scanner.next();
				i=student.setLastname(lastname);
			}while(i==true);
			
			System.out.println("Enter Username -");
			do {
				QuizDemo.scanner=new Scanner(System.in);
				username=QuizDemo.scanner.next();
				i=student.setUsername(username);
			}while(i==true);
			
			
			System.out.println("Enter password -");
			do {
				QuizDemo.scanner=new Scanner(System.in);
				password=QuizDemo.scanner.next();
				i=student.setPassword(password);
			}while(i==true);
			
			System.out.println("Enter City -");
			do {
				QuizDemo.scanner=new Scanner(System.in);
				city=QuizDemo.scanner.next();
				i=student.setCity(city);
			}while(i==true);
				
			System.out.println("Enter e-mail-id -");
			do {
				QuizDemo.scanner=new Scanner(System.in);
				emailId=QuizDemo.scanner.next();
				i=student.setEmailId(emailId);
			}while(i==true);
			
			System.out.println("Enter Mobile-Number -");
			do {
				QuizDemo.scanner=new Scanner(System.in);
				mobileNumber=QuizDemo.scanner.next();
				i=student.setMobileNumber(mobileNumber);
			}while(i==true);
			
			preparedStatement.setString(1,firstname);
			preparedStatement.setString(2,lastname );
			preparedStatement.setString(3,username );
			preparedStatement.setString(4,password );
			preparedStatement.setString(5,city );
			preparedStatement.setString(6,emailId);
			preparedStatement.setString(7,mobileNumber);
				
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
