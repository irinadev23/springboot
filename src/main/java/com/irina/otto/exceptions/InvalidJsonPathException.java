package com.irina.otto.exceptions;

import  java.lang.RuntimeException;

public class InvalidJsonPathException extends RuntimeException{
	public InvalidJsonPathException(String errorMessage) {
	        super(errorMessage);
	    }
}
