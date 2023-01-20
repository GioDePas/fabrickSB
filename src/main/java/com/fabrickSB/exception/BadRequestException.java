package com.fabrickSB.exception;

import com.fabrickSB.model.ErrorResponseList;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = -2901421369287682731L;
	private ErrorResponseList error;

	public BadRequestException(ErrorResponseList error) {
		this.error = error;
	}

	public ErrorResponseList getError() {
		return error;
	}

	public void setError(ErrorResponseList error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "BadRequestException [error=" + error + "]";
	}
	
}
