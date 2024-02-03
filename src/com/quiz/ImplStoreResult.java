package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplStoreResult implements StoreResult {

	Connection connection=null;
	PreparedStatement ps = null;
	
	@Override
	public void getStoreResult() {
	  try {
		throw new AccessDeniedForResultException("Sorry! cannot store result directly...."+"\n"+"please attempt the quiz first....");
	  }catch(Exception e) {
		System.out.println(e.getMessage());;
	  }
	}

	public void setStudentMarksInDB(String username,int obtainedMarksOfStudent,int totalMarksOfQuiz) throws SQLException {
		try {
		// calling of connectionDetails method to establish connection
		ConnectionDetails connectionDetails = new ConnectionDetails();
		connection=connectionDetails.getConnection();
		String obtainedMarks=obtainedMarksOfStudent+"/"+totalMarksOfQuiz;
		// System.out.println("\n"+"Total marks of student: "+total_marks);
		ps=connection.prepareStatement("insert into student_result(username,score) value (?,?)");	
		ps.setString(1, username);
		ps.setString(2, obtainedMarks);
		int i=ps.executeUpdate();
		}catch(Exception e) {
		System.out.println(e.getMessage());
		}
		finally {
			connection.close();
			ps.close();
		}
	}
}
