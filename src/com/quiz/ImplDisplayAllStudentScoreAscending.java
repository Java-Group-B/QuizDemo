
package com.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImplDisplayAllStudentScoreAscending implements DisplayAllStudentScoreAscending {

			@Override
			public void getDisplayAllStudentScoreAscending() throws Exception {

				System.out.println("Enter the username :");
				String uname=QuizDemo.scanner.next();
				System.out.println("Enter the password :");
				String pword=QuizDemo.scanner.next();
				

AdminCredentials adminCredentials=new AdminCredentials();
boolean varify=adminCredentials.getAdminVerification(uname, pword);
//if credentials are correct, then call addNewQuestion method
if(varify==true) {
	displayresulbyasc();	
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
			
public static void displayresulbyasc() throws Exception {
	Connection con = null;
	PreparedStatement ps = null;
	try {

		ConnectionDetails cd = new ConnectionDetails();
		con=cd.getConnection();
		/*// step-1
		Class.forName("com.mysql.jdbc.Driver");
		// step-2
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group_b_db", "root","root");// Q
		// step-3-*/
		 ps = con.prepareStatement("select student_data.firstname,student_data.lastname,student_result.score from student_data inner join student_result on student_data.username= student_result.username ORDER BY student_result.score ASC;");

		// step-4-
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			
			String firstname = rs.getString("firstname");
			System.out.println("firstname>>" + firstname);
			String lastname = rs.getString("lastname");
			System.out.println("lastname>>" + lastname);
			
			String score = rs.getString("score");
			System.out.println("Score =" + score );
			System.out.println();
		}
		// step-5-closing  all  resources
		
	
	} catch (Exception e) {
	System.out.println(e.getMessage());	
	}
	finally {
		con.close();
		ps.close();
		
	}
	
	
}

		
	}



