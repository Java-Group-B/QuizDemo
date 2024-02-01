package com.quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImplDisplayQuestionsList implements DisplayQuestionsList {
	Connection connection = null;
	PreparedStatement ps = null;
	PreparedStatement ps1=null;
	int countOfCorrectQuestions=0;
	int countOfTotalNoQuestions=0;
	int totalMarksOfStudent=0;

	public void getUserLogin() throws SQLException {
		// take input from user i.e username and password
		System.out.println("Enter the username :");
		String username=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String password=QuizDemo.scanner.next();
		// call upon the getUserVarification() method and verify username and password
		boolean verificationOfData=VerificationOfUserLoginData.getUserVarification(username,password);
		//if credentials are correct, then call getResult to display result 
		try{
		if(verificationOfData==true) {
		totalMarksOfStudent=getDisplayQuestionsList();
			// calling of connectionDetails method to establish connection
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection = connectionDetails.getConnection();
			// selection of all no of questions from question_table
			ps=connection.prepareStatement("insert into student_marks(username,user_password,user_marks) value (?,?,?)");
		}
		}catch(Exception e) {
			e.printStackTrace();
	}
		}

@Override
public int getDisplayQuestionsList() throws SQLException {
		getUserLogin();
	try{
		// calling of connectionDetails method to establish connection
		ConnectionDetails connectionDetails = new ConnectionDetails();
		connection = connectionDetails.getConnection();
		// selection of all no of questions from question_table
		ps=connection.prepareStatement("select * from question_bank");
		ResultSet rs=ps.executeQuery();  // execution of query
		// check if question is present in database and return true if it is present or return false
		while(rs.next()) {
			// To count no of questions present in database group_b_db in question_table
			countOfTotalNoQuestions++;
		}
		// System.out.println(countOfTotalNoQuestions);
		// selection of question one by one
		ps1 = connection.prepareStatement("select * from question_bank where id =?");
		// to select question at particular id
		for(int i=1;i<=countOfTotalNoQuestions;i++) {
		  ps1.setInt(1, i); 
		  ResultSet rs1=ps1.executeQuery();	   // execution of query		
			while(rs1.next()) {
				// print of question and its option
				System.out.println("\n"+"Question."+rs1.getInt(1)+" "+rs1.getString(2));
				System.out.println("Marks:5");
				System.out.println("a."+rs1.getString(3));
				System.out.println("b."+rs1.getString(4));
				System.out.println("c."+rs1.getString(5));
				System.out.println("d."+rs1.getString(6)+"\n");
			// the check if the input is valid or not
			int j=1;
				while(j>0) {
				Scanner scanner=new Scanner(System.in);
				System.out.print("Enter your answer: ");
				char userAnswer=scanner.next().charAt(0);
				if(userAnswer=='a' || userAnswer=='b'||userAnswer=='c'||userAnswer=='d') {
				String userAnswer2=String. valueOf(userAnswer); 
				//System.out.println(userAnswer2);
				String correct_answer= rs1.getString(7);
				//System.out.println(correct_answer);
				if(userAnswer2.equals(correct_answer)) {
				 countOfCorrectQuestions++;
				}
				break;
				}else {
				 j++;
				 try {
					throw new InvalidInputForAnswerException("please enter the valid input...."+"\n");
				 }catch(Exception e) {
					System.err.println(e);
					}
				continue;
				}
			}
		}
	}
		
//	System.out.println("\n"+"Total correct questions: "+countOfCorrectQuestions+"/"+countOfTotalNoQuestions);
	totalMarksOfStudent=5*countOfCorrectQuestions;
	System.out.println("Total marks of student: "+totalMarksOfStudent+" out of "+countOfTotalNoQuestions*5);
	System.out.println("Thank you for this Quiz....!! ");	 
}catch (Exception e) {
	  e.printStackTrace();
	}
	finally {
	  connection.close();
	  ps.close();
	  ps1.close();
	}
	return totalMarksOfStudent;
}
}	

