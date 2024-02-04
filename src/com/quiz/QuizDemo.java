package com.quiz;

import java.util.Scanner;

public class QuizDemo {
static Scanner scanner=null;
	public static void main(String[] args) throws Exception {
		getQuiz();
	}
	
	public static void getQuiz() throws Exception{
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
	
	
	int choice=0;
	boolean flag=false;
do {
	flag=true;
	try {
	scanner=new Scanner(System.in);
	System.out.println("Enter your choice : ");
	choice=scanner.nextInt();
	if(!(choice>=1 && choice<=8)) {
		throw new InvalidChoiceException();
	}
	}catch(Exception e) {
		System.out.println("Please enter valid choice (1 to 8)");
		flag=false;
	}
}while(flag==false);

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
	}
	
	System.out.println("Do you want to perform more activities on quiz application ?\npress \"y\" key for yes,press any other key to exit.");
	char c=scanner.next().charAt(0);
	if(c=='y' || c=='Y') {
		getQuiz();
	}else {
		System.out.println("Thank you... Have a good day!!!");
	}
	
	scanner.close();
}
}
