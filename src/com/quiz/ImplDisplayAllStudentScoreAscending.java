
package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImplDisplayAllStudentScoreAscending implements DisplayAllStudentScoreAscending {
	/*getDisplayAllStudentScoreAscending() method is used to verify Admin Details*/
	/*Author Name-Priyanka Suyog Gawali*/
	@Override
	public void getDisplayAllStudentScoreAscending() throws Exception {
		System.out.println("Enter the admin username :");
		String uname=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String pword=QuizDemo.scanner.next();
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		if(varify==true) {
			displayResultByAsc();	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);
			if(isNotStudent==true) {
				System.out.println("Access Denied for students...");
				System.out.println("\nPlease enter \"admin\" credentials :");
				getDisplayAllStudentScoreAscending();
			}
			else {
				getDisplayAllStudentScoreAscending();
			}
		}
	}	

	/*displayResultByAsc()-method is used to display result by ascending order of Score */
	/*Author Name-Priyanka Suyog Gawali*/	
	public static void displayResultByAsc() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			ConnectionDetails cd = new ConnectionDetails();
			con=cd.getConnection();
			System.out.println("-------Student score by Ascending order-------");
			ps = con.prepareStatement("select student_data.firstname,student_data.lastname,student_result.score from student_data inner join student_result on student_data.username= student_result.username ORDER BY student_result.score ASC;");
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
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			con.close();
			ps.close();

		}
	}
}



