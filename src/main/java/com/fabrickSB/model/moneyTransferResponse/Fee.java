package com.fabrickSB.model.moneyTransferResponse;

public class Fee {

	private String feeCode;
    private String description;
    private Number amount;
    private String currency;
    
	public Fee(String feeCode, String description, Number amount, String currency) {
		this.feeCode = feeCode;
		this.description = description;
		this.amount = amount;
		this.currency = currency;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Number getAmount() {
		return amount;
	}

	public void setAmount(Number amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Fee [feeCode=" + feeCode + ", description=" + description + ", amount=" + amount + ", currency="
				+ currency + "]";
	}
	
}
