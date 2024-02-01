package com.quiz;

public class IncorrectUsernameOrPasswordException extends RuntimeException{

	public IncorrectUsernameOrPasswordException(String err_message) {
		super(err_message);
	}
}
