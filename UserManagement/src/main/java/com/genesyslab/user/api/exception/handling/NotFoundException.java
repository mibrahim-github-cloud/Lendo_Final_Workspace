package com.genesyslab.user.api.exception.handling;

public class NotFoundException extends RuntimeException{
	
	private String message;

	public NotFoundException(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3693216289783377879L;
	
	

}
