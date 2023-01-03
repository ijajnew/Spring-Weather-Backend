package com.etravali.assignment.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
	
	//Adding exception hander to catch all exceptions
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(RuntimeException exc){
		
		UserErrorResponse errorResponse = new UserErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), exc.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
