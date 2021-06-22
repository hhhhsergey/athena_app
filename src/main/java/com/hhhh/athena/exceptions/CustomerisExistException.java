package com.hhhh.athena.exceptions;

public class CustomerisExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomerisExistException(String errormessage) {
		super(errormessage);
	}
}
