package com.irina.otto.exceptions;

import  java.lang.RuntimeException;

public class InvalidRegionException extends RuntimeException{
	
	public InvalidRegionException(String errorMessage) {
	        super(errorMessage);
	    }
}
