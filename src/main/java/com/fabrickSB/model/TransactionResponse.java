package com.fabrickSB.model;

import java.util.List;

public class TransactionResponse {
	
	private String status;
	private TransactionPayload payload;
	
	private List<Object> error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TransactionPayload getPayload() {
		return payload;
	}

	public void setPayload(TransactionPayload payload) {
		this.payload = payload;
	}

	public List<Object> getError() {
		return error;
	}

	public void setError(List<Object> error) {
		this.error = error;
	}
	
}
