package com.quiz;

import java.util.Scanner;

public class QuizDemo {
static Scanner scanner=null;
	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to Quiz based application"
				+ "\nUser Operation:"
				+ "\n1. Student Registration"
				+ "\n2. Student Login"
				+ "\n3. Display the list of questions"
				+ "\n4. Store Quiz result into database"
				+ "\n5. Display Quiz result "
				+ "\nAdmin Operation:"
				+ "\n6. Display all students score as per ascending order"
				+ "\n7. Fetch student score by using id"
				+ "\n8. Add question with 4 options into database\n");
	System.out.println("Enter your choice : ");
	scanner=new Scanner(System.in);
	int choice=scanner.nextInt();
	switch(choice) {
	case 1:
		StudentRegistration studentRegistration=new ImplStudetRegistration();
		studentRegistration.getStudentRegistration();
		break;
	case 2:
		StudentLogin studentLogin=new ImplStudentLogin();
		studentLogin.getStudentLogin();
		break;
	case 3:
		DisplayQuestionsList displayQuestionsList=new ImplDisplayQuestionsList();
		displayQuestionsList.getDisplayQuestionsList();
		break;
	case 4:
		StoreResult storeResult=new ImplStoreResult();
		storeResult.getStoreResult();
		break;
	case 5:
		DisplayResult displayResult=new ImplDisplayResult();
		displayResult.getDisplayResult();
		break;
	case 6:
		DisplayAllStudentScoreAscending displayAllStudentScoreAscending=new ImplDisplayAllStudentScoreAscending();
		displayAllStudentScoreAscending.getDisplayAllStudentScoreAscending();
		break;
	case 7:
		ScoreById scoreById=new ImplScoreById();
		scoreById.getScoreById();
		break;
	case 8:
		AddQuestion addQuestion=new ImplAddQuestion();
		addQuestion.getAddQuestion();
		break;
	default:
		System.out.println("Invalid Choice.");		
	}
	scanner.close();
	}
	
}
