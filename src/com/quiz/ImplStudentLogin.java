package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class ImplStudentLogin implements StudentLogin {

	@Override
	public void getStudentLogin() throws Exception {

		//Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			System.out.println("Enter Username : ");
			String uname = QuizDemo.scanner.next();
			System.out.println("Enter Password : ");
			String pwd = QuizDemo.scanner.next();
			ConnectionDetails connectionDetails = new ConnectionDetails();
			connection =connectionDetails.getConnection();
			preparedStatement = connection.prepareStatement("select username,password from student_data");
			resultSet = preparedStatement.executeQuery(); 
			boolean i =false;
			Map<String,String> loginData = new LinkedHashMap<String, String>(); //to store username & password 
			while(resultSet.next()) {
				loginData.put(resultSet.getString("username"),resultSet.getString("password")); // putting data into key value pair 
			}
			for(Map.Entry<String,String> map : loginData.entrySet()) {
				if(map.getKey().equals(uname) && map.getValue().equals(pwd)) {
					i = true;
				}
			}
			if(i==true) {
				System.out.println("Login Successful...!!!");
			}else {
				throw new Exception();
			}
			System.out.println("\nDo you want to attempt Quiz or Do you want to check result?\n(Press \"y\" key for yes, press any other key to exit.): ");
			String ans = QuizDemo.scanner.next();
			// we have to add 3 and 5 number step here
			if(ans.equalsIgnoreCase("y")) {
				System.out.println("Which activity you want to perform : \n1. Attempt Quiz \n2. Display Quiz Result : ");
			//	System.out.println("Enter your choice: ");
				
					System.out.println("Enter your choice: ");
					String option = QuizDemo.scanner.next();

				do {	
				if(!(option.equals("1")||option.equals("2"))) {
					System.out.println("Invalid choice....\n\nEnter your correct choice :");
					 option = QuizDemo.scanner.next();	 
				}
				}while(!(option.equals("1")||option.equals("2")));
				//int option = QuizDemo.scanner.nextInt();
				if(option.equals("1")) {
					ImplDisplayQuestionsList questions = new ImplDisplayQuestionsList();
					questions.setQuizInitialization(uname);
				}
				else if(option.equals("2")) {
					boolean verifyUser=VerificationOfUserLogin.getUserLoginVerification(uname, pwd);
					/*call getUserVarification method and verify user*/
							if(verifyUser==true) {		
					boolean varify=VerificationOfLoginDataForResult.getUserVarification(uname,pwd);
					//if credentials are correct, then call getResult to display result 
					if(varify==true) {
						ImplDisplayResult.getResult(uname,pwd);	
					}
//					try {
					else{
						try {
							/*throw exception if credentials not match*/
							throw new QuizNotAttemptedException("You have not attempted Quiz.");
						}
						catch(QuizNotAttemptedException q) {
							System.out.println(q.getMessage());
						}
							}
					}
				}
			}			
		}
		catch(Exception e) {
			System.out.println("\nUsername Or Password is incorrect.\n\nPlease try again..");
			getStudentLogin();
		}
		finally {
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}
//			else {
//				throw new Exception();
//			}
//			System.out.println("\nDo you want to attempt Quiz or Do you want to check result?\n(Press \"y\" key for yes, press any other key to exit.): ");
//			String ans = QuizDemo.scanner.next();
//			// we have to add 3 and 5 number step here
//			if(ans.equalsIgnoreCase("y")) {
//				System.out.println("Which activity you want to perform : \n1. Attempt Quiz \n2. Display Quiz Result : ");
//			//	System.out.println("Enter your choice: ");
//				
//					System.out.println("Enter your choice: ");
//					String option = QuizDemo.scanner.next();
//
//				do {	
//				if(!(option.equals("1")||option.equals("2"))) {
//					System.out.println("Invalid choice....\n\nEnter your correct choice :");
//					 option = QuizDemo.scanner.next();	 
//				}
//				}while(!(option.equals("1")||option.equals("2")));
//				//int option = QuizDemo.scanner.nextInt();
//				if(option.equals("1")) {
//					ImplDisplayQuestionsList questions = new ImplDisplayQuestionsList();
//					questions.setQuizInitialization(uname);
//				}
//				else if(option.equals("2")) {
//					boolean verifyUser=VerificationOfUserLogin.getUserLoginVerification(uname, pwd);
//					if(verifyUser==true) {
//              		boolean varify=VerificationOfLoginDataForResult.getUserVarification(uname,pwd);
//              		
//						if(varify==true) {
//							ImplDisplayResult.getResult(uname,pwd);	
//						}
//					}
//					
//				}
//			}
//			
//		}
//		catch(Exception e) {
//			System.out.println("\nUsername Or Password is incorrect.\n\nPlease try again..");
//			getStudentLogin();
//		}
//		finally {
//			connection.close();
//			preparedStatement.close();
//			resultSet.close();
//		}

	}

}