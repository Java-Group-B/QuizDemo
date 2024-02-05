package com.quiz;
/*This is just Custom Exception*/
public class ReAttemptOfQuizIsDeniedException extends RuntimeException {

	public ReAttemptOfQuizIsDeniedException(String err_messege){
		super(err_messege);
	}
}