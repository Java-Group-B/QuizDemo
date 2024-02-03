package com.quiz;

public class QuizNotAttempted extends RuntimeException{

	public QuizNotAttempted(String err_message) {
		super(err_message);
	}
}
