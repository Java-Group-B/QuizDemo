package com.quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ImplDisplayQuestionsList implements DisplayQuestionsList {
	// connection objects
	Connection connection = null;
	PreparedStatement ps = null;
	PreparedStatement ps1=null;
	
	// variables defined in program to count
	int countOfCorrectQuestions=0;
	int countOfTotalNoQuestions=0;
	
	@Override
	public void getDisplayQuestionsList() throws SQLException {
		System.out.println("Enter the username :");
		String username=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String password=QuizDemo.scanner.next();
		
		VerificationOfUserLogin verificationOfUserLogin=new VerificationOfUserLogin();
		boolean userDataVerify=verificationOfUserLogin.getUserLoginVerification(username,password);
		if(userDataVerify==true) {
			setQuizInitialization(username);
		}else {
			throw new IncorrectUsernameOrPasswordException("Incorrect username or password...plz try again...or register again!!!");
		}
	}
	
public void setQuizInitialization(String username) throws SQLException {
		
	try{
		// calling of connectionDetails method to establish connection
		ConnectionDetails connectionDetails = new ConnectionDetails();
		connection = connectionDetails.getConnection();
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
	int obtainedMarksOfStudent=5*countOfCorrectQuestions;
	int totalMarksOfQuiz=10*5;
	// create object of ImplStoreResult class
	ImplStoreResult implStoreResult = new ImplStoreResult();
	implStoreResult.setStudentMarksInDB(username,obtainedMarksOfStudent,totalMarksOfQuiz);
	
	System.out.println("Thank you for this Quiz....!! ");	 
}catch (Exception e) {
	  e.printStackTrace();
	}
	finally {
	  connection.close();
	  ps.close();
	  ps1.close();
	}
}
}

