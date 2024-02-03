package com.quiz;

public class QuizNotAttemptedException extends RuntimeException{

	public QuizNotAttemptedException(String err_message) {
		super(err_message);
	}
}
