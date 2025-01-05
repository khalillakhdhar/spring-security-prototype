package com.elitetech.springsecurity.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex)
	{
		
		Map<String, String> errors=new HashMap<String, String>();
		for(FieldError error: ex.getBindingResult().getFieldErrors())
		{
			errors.put(error.getField(),error.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception ex)
	{
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("erreur innatendu s'est produite: "+ex.getMessage());
	}
	
}

