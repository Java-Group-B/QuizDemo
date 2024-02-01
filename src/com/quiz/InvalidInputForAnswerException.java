package com.quiz;

public class InvalidInputForAnswerException extends RuntimeException{

	public InvalidInputForAnswerException(String error_messege) {
		super(error_messege);
	}	
	}