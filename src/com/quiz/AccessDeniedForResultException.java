package com.quiz;

public class AccessDeniedForResultException extends RuntimeException {
	public AccessDeniedForResultException(String error_msg) {
		super(error_msg);
	}

}
