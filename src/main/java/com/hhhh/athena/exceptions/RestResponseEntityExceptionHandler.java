package com.hhhh.athena.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ CustomerisExistException.class })
	public ResponseEntity<ExceptionResponse> handleAccessDeniedException(CustomerisExistException ex) {
		ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
		
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
}
