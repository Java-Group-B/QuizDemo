package com.quiz;
/*This is just Custom Exception*/
public class QuizNotAttemptedException extends RuntimeException{

	public QuizNotAttemptedException(String err_message) {
		super(err_message);
	}
}
