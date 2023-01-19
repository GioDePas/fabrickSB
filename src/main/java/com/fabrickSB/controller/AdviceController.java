package com.fabrickSB.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fabrickSB.model.ErrorResponse;
import com.fabrickSB.model.ErrorResponseList;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseList> exeption(MethodArgumentNotValidException ex) {
		
		
		
		List<ErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream().map(err -> new ErrorResponse(err.getDefaultMessage())).toList();
		
		ErrorResponseList responseList = new ErrorResponseList();
		
		responseList.setErrors(errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(responseList);
		
		}
	
}
