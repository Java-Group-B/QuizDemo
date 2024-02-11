package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplStoreResult implements StoreResult {

	Connection connection=null;
	PreparedStatement ps = null;
	/*getStoreResult() method will throw exception,
	  if someone trying to store result directly(without attempting Quiz)*/
	/*Author Name- Himanshu*/
	@Override
	public void getStoreResult() {
		try {
			throw new AccessDeniedForResultException("Sorry! cannot store result directly...."+"\n"+"please attempt the quiz first....");
		}catch(Exception e) {
			System.out.println(e.getMessage());;
		}
	}
	
	/*setStudentMarksInDB() method is used to store Quiz result in Database*/
	/*Author Name-Himanshu */	
	public void setStudentMarksInDB(String username,int obtainedMarksOfStudent,int totalMarksOfQuiz) throws SQLException {
		try {
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection=connectionDetails.getConnection();
			String obtainedMarks=obtainedMarksOfStudent+"/"+totalMarksOfQuiz;
			ps=connection.prepareStatement("insert into student_result(username,score) values (?,?)");	
			ps.setString(1, username);
			ps.setString(2, obtainedMarks);
			ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			connection.close();
			ps.close();
		}
	}
}
