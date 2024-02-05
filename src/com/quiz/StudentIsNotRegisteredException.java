package com.quiz;
/*This is just Custom Exception*/
public class StudentIsNotRegisteredException extends RuntimeException {

public StudentIsNotRegisteredException(String err_messege) {
	super(err_messege);
}
}