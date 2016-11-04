package com.learning.exceptions;

public class InvalidEmailException extends Exception {

	private static final long serialVersionUID = 8385950106327598161L;

	public InvalidEmailException(String message){
		super(message);
	}
}
