package com.fabrickSB.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fabrickSB.exception.BadRequestException;
import com.fabrickSB.exception.ForbiddenException;
import com.fabrickSB.model.ErrorResponse;
import com.fabrickSB.model.ErrorResponseList;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseList> exeption(MethodArgumentNotValidException ex) {
		
		List<ErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream().map(err -> new ErrorResponse("Invalid", err.getDefaultMessage(), err.getField())).toList();
		
		ErrorResponseList responseList = new ErrorResponseList("KO", errors);
		
		responseList.setErrors(errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(responseList);
		
		}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponseList> exeption(BadRequestException ex) {	
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ex.getError());
		
		}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponseList> exception(ForbiddenException ex) {
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(ex.getError());
		
	}
	
	//GENERICA
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseList> exeption(Exception ex) {	
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(new ErrorResponseList("KO"));
		
	}
	
}
