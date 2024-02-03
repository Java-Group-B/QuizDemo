package com.quiz;

public class ReAttemptOfQuizIsDeniedException extends RuntimeException {

	public ReAttemptOfQuizIsDeniedException(String err_messege){
		super(err_messege);
	}
}