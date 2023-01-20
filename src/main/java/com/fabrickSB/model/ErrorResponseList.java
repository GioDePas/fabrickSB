package com.fabrickSB.model;

import java.util.List;

public class ErrorResponseList {
	
	private String status;
	private List<ErrorResponse> errors;
	private Object payload;
	
	public ErrorResponseList() {}
	
	public ErrorResponseList(String status) {
		this.status = status;
	}

	public ErrorResponseList(String status, List<ErrorResponse> errors) {
		this.status = status;
		this.errors = errors;
	}
	
	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorResponse> errors) {
		this.errors = errors;
	}
	
}
