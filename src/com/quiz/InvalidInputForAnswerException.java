package com.quiz;
/*This is just Custom Exception*/
public class InvalidInputForAnswerException extends RuntimeException{

	public InvalidInputForAnswerException(String error_messege) {
		super(error_messege);
	}	
	}