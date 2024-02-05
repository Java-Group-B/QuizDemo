package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ImplScoreById implements ScoreById {
	Connection connection=null;
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
	/*getScoreById() method-is used to verify admin verification*/
	/*Author Name-Pranoti*/
	@Override
	public void getScoreById() throws Exception{
		System.out.println("Enter the admin username :");
		String uname=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String pword=QuizDemo.scanner.next();
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		if(varify==true) {
			getScoreByAdmin() ;	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);

			if(isNotStudent==true) {
				System.out.println("Access Denied for students...");
				System.out.println("\nPlease enter \"admin\" credentials :");
				getScoreById();		
			}
			else {
				getScoreById();
			}
		}
	}
	
	/*getScoreByAdmin() method is used to fetch result of multiple user.*/
	/*Author Name-Pranoti*/	
	public void getScoreByAdmin() throws Exception {
		System.out.println("Hellow Admin.........!!!");
		boolean i=false;
		while(i==false) {
			System.out.println("Enter the id of a student to get result:");
			int studentId=QuizDemo.scanner.nextInt();
			try {
				ConnectionDetails connectionDetails = new ConnectionDetails();
				connection = connectionDetails.getConnection();
				ps1=connection.prepareStatement("select student_id, username from student_data");
				ResultSet rs1=ps1.executeQuery();
				Map<Integer,String> listOfStudentId=new HashMap<Integer,String>();
				while(rs1.next()) {
					listOfStudentId.put(rs1.getInt(1),rs1.getString(2));
				}
				Set<Integer> set=listOfStudentId.keySet();
				boolean present=set.contains(studentId);  // 102
				if(present==true) {
					boolean v=VerificationOfUsernameForAdmin.setVerificationOfUsername(listOfStudentId.get(studentId));
					if(v==true) {
						getScoreByStudentId(studentId);
					}else {
						try {
							throw new QuizNotAttemptedException("Student id : "+studentId+" is registered...but quiz not attempted...");
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}else {
					try {
						throw new StudentIsNotRegisteredException("Student id : "+studentId+" does not exist......");
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}	

			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally {
				ps1.close();
				connection.close();
			}

			System.out.println("\nDo you want to see result of other student?\nPlease enter y for yes, any other key to exit");
			QuizDemo.scanner=new Scanner(System.in);
			char input=QuizDemo.scanner.next().charAt(0);
			if(input=='y') {
				i=false;
				continue;
			}else {
				break;
			}
		}	
	}

	/*getScoreByStudentId() -method is used to fetch result,if user is verified*/
	/*Author Name-Pranoti*/
	public void getScoreByStudentId(int studentId) throws SQLException {
		try{
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection = connectionDetails.getConnection();
			ps2 = connection.prepareStatement("Select student_data.student_id,student_result.score from student_data inner join student_result on student_data.username=student_result.username where student_id=?");
			ps2.setInt(1,studentId);
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				String score=rs2.getString(2);
				System.out.println("Your score is "+score);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			connection.close();
			ps2.close();
		}
	}
}
