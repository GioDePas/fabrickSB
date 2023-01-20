package com.fabrickSB.exception;

import com.fabrickSB.model.ErrorResponseList;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = -3224233561364457224L;
	private ErrorResponseList error;

	public ForbiddenException(ErrorResponseList error) {
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
		return "ForbiddenExeption [error=" + error + "]";
	}
	
}
