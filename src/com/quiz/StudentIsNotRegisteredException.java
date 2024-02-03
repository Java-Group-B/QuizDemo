package com.quiz;

public class StudentIsNotRegisteredException extends RuntimeException {

public StudentIsNotRegisteredException(String err_messege) {
	super(err_messege);
}
}