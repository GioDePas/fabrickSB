package com.fabrickSB.model;

import java.util.List;

public class AccountBalanceResponse {

	private String status;
	private AccountBalance payload;
	
	private List<Object> error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AccountBalance getPayload() {
		return payload;
	}

	public void setPayload(AccountBalance payload) {
		this.payload = payload;
	}

	public List<Object> getError() {
		return error;
	}

	public void setError(List<Object> error) {
		this.error = error;
	}
	
	
}
