package com.etravali.assignment.rest;

public class UserErrorResponse {
	
	private String Status;	
	private String Message;
	
	public UserErrorResponse(String status, String message) {
		Status = status;
		Message = message;
	}
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}

}
