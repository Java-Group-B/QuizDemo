package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ImplScoreById implements ScoreById {
	// connection objects
	Connection connection=null;
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
		
	@Override
	public void getScoreById() throws Exception{
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
			getScoreByAdmin() ;	
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
		
public void getScoreByAdmin() throws Exception {
	
	System.out.println("Hellow Admin.........!!!");
	System.out.println("Enter the id of a student to get result:");
	
	int studentId=QuizDemo.scanner.nextInt();
	try {
	// calling of connectionDetails method to establish connection
	ConnectionDetails connectionDetails = new ConnectionDetails();
	connection = connectionDetails.getConnection();
	ps1=connection.prepareStatement("select student_id, username from student_data");
	ResultSet rs1=ps1.executeQuery();
	
Map<Integer,String> listOfStudentId=new HashMap<Integer,String>();
while(rs1.next()) {
  listOfStudentId.put(rs1.getInt(1),rs1.getString(2));
}
Set<Integer> set=listOfStudentId.keySet();
//for(int i=0;i<=set.size();i++) {
boolean present=set.contains(studentId);  // 102
//System.out.println(present);
if(present==true) {
  boolean v=VerificationOfUsernameForAdmin.setVerificationOfUsername(listOfStudentId.get(studentId));
  if(v==true) {
	getScoreByStudentId(studentId);
	//System.out.println(v);
  }else {
	  try {
		  //System.out.println(v);
		throw new QuizNotAttemptedException("Student id : "+studentId+" is registered...but quiz not attempted...");
	   }catch(Exception e) {
		System.out.println(e.getMessage());
		}
}
		// break;
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
}

	public void getScoreByStudentId(int studentId) throws SQLException {
		try{
			// calling of connectionDetails method to establish connection
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
