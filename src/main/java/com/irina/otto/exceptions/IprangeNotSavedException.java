package com.irina.otto.exceptions;

import  java.lang.RuntimeException;

public class IprangeNotSavedException extends RuntimeException{
	public IprangeNotSavedException(String errorMessage) {
	        super(errorMessage);
	    }
}
