package com.fabrickSB.model.moneyTransferResponse;

import java.util.List;

public class MoneyTransferPayload {

	private String status;
	private MoneyTransferResponse payload;
	private List<Object> error;
	
	
	
	public MoneyTransferPayload() {}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public MoneyTransferResponse getPayload() {
		return payload;
	}
	public void setPayload(MoneyTransferResponse payload) {
		this.payload = payload;
	}
	public List<Object> getError() {
		return error;
	}
	public void setError(List<Object> error) {
		this.error = error;
	}
	
}
