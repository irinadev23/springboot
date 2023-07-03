package com.irina.otto.exceptions;


import  java.lang.RuntimeException;

public class ProblemReadingJsonException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProblemReadingJsonException(String errorMessage) {
	        super(errorMessage);
	    }
}
