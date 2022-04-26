package com.genesyslab.user.api.exception.handling;

import java.util.Date;

public class ErrorMessage {

	private String message;
	private Date timestamp;
	
	public ErrorMessage(String message, Date date) {
		this.message = message;
		this.timestamp = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
