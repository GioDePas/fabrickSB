package com.fabrickSB.model;

public class ErrorResponse extends TransactionResponse {

	private String message;
	
	public ErrorResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
