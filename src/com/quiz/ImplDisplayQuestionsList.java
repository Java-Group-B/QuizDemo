package com.quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ImplDisplayQuestionsList implements DisplayQuestionsList {

	Connection connection = null;
	PreparedStatement ps1=null;
	int countOfCorrectQuestions=0;
	int counter=0;
	/*getDisplayQuestionsList() method is used to verify user details*/	
	/*Author Name-Pranoti*/
	@Override
	public void getDisplayQuestionsList() throws Exception {
		System.out.println("Enter the username :");
		String username=QuizDemo.scanner.next();
		System.out.println("Enter the password :");
		String password=QuizDemo.scanner.next();

		boolean userDataVerify=VerificationOfUserLogin.getUserLoginVerification(username,password);
		if(userDataVerify==true) {
			boolean i=VerificationOfLoginDataForResult.getUserVarification(username,password);
			if(i==true) {
				try {
					throw new ReAttemptOfQuizIsDeniedException("sorry! you cannot re-attempt the quiz again....");
				}catch(ReAttemptOfQuizIsDeniedException e) {
					System.out.println(e.getMessage());;
				}
			}else {
				setQuizInitialization(username);
			}

		}else {
			System.out.println("Please Enter Correct Username and Password :\n");
			 getDisplayQuestionsList();
		}
	}
	/*setQuizInitialization() method is used to display questions and start the quiz if user is verified*/
	/*Author Name-Pranoti*/
	public void setQuizInitialization(String username) throws Exception {
		System.out.println("------------------- BEST LUCK FOR THE QUIZ, "+username+"!!!!-------------------");
		Set<Question> questionSet= new HashSet<Question>();
		try{
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection = connectionDetails.getConnection();
			ps1 = connection.prepareStatement("select question_id,question,option_a,option_b,option_c,option_d,correct_answer from question_bank order by RAND() Limit 10;");
			ResultSet rs1=ps1.executeQuery();	   
			while(rs1.next()) {
				Question q1=new Question();
				q1.setQuestionId(rs1.getInt(1));
				q1.setQuestion(rs1.getString(2));
				q1.setOption_a(rs1.getString(3));
				q1.setOption_b(rs1.getString(4));
				q1.setOption_c(rs1.getString(5));
				q1.setOption_d(rs1.getString(6));
				q1.setAnswers(rs1.getString(7));
				questionSet.add(q1);		
			}
			Iterator<Question> itr=questionSet.iterator();
			int i=1;
			while(itr.hasNext()) {
				Question q1=itr.next();
				System.out.println("\n"+"Question."+i+" "+q1.getQuestion());
				System.out.println("Marks:5");
				System.out.println("a."+q1.getOption_a());
				System.out.println("b."+q1.getOption_b());
				System.out.println("c."+q1.getOption_c());
				System.out.println("d."+q1.getOption_d()+"\n");
				int j=1;
				while(j>0) {
					System.out.print("Enter your answer: ");
					char userAnswer=QuizDemo.scanner.next().charAt(0);
					if((userAnswer>=65 && userAnswer<=68) || (userAnswer>=97 && userAnswer<=100)) {
						String userAnswer2=String. valueOf(userAnswer); 
						String correct_answer= q1.getAnswers();
						if(userAnswer2.equalsIgnoreCase(correct_answer)) {
							countOfCorrectQuestions++;
						}
						break;
					}else {
						j++;
						try {
							throw new InvalidInputForAnswerException("please enter the valid option...."+"\n");
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
						continue;
					}
				}
				i++;
			}
			int obtainedMarksOfStudent=5*countOfCorrectQuestions;
			int totalMarksOfQuiz=10*5;
			ImplStoreResult implStoreResult = new ImplStoreResult();
			implStoreResult.setStudentMarksInDB(username,obtainedMarksOfStudent,totalMarksOfQuiz);
			System.out.println("------------ Thank you for this Quiz, "+username+"...!!------------"+"\n");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			connection.close();
			ps1.close();
		}
	}
}