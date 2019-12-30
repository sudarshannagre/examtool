package com.sud.exam.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

@ControllerAdvice
public class ExamToolServiceExceptions {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e){
		return exception(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse("Internal Server Error", e.getMessage()));
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ErrorResponse> handleSQLException(SQLException e){
		return exception(HttpStatus.BAD_REQUEST, new ErrorResponse("Bad Request", e.getMessage()));
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRecordNotFound(RecordNotFoundException e){
		return exception(HttpStatus.NOT_FOUND, new ErrorResponse("Record Not Found", e.getMessage()));
	}
		
	@ExceptionHandler(MethodNotAllowedException.class)
	public ResponseEntity<ErrorResponse> handleMethodNotAllowed(MethodNotAllowedException e){
		return exception(HttpStatus.METHOD_NOT_ALLOWED, new ErrorResponse("Method Not Allowed", e.getMessage()));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException u){
		return exception(HttpStatus.NOT_FOUND, new ErrorResponse("User Not Found", u.getMessage()));
	}
	
	private ResponseEntity<ErrorResponse> exception(HttpStatus status, ErrorResponse e){
		return new ResponseEntity<>(e,status);
	}
}
