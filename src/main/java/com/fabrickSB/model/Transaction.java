package com.fabrickSB.model;

import java.util.Date;

public class Transaction {

	private String transactionId;
    private String operationId;
    private Date accountingDate;
    private Date valueDate;
    private Type type;
    private Number amount;
    private String currency;
    private String description;
    
	public Transaction() {}
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	public Date getAccountingDate() {
		return accountingDate;
	}
	public void setAccountingDate(Date accountingDate) {
		this.accountingDate = accountingDate;
	}
	
	public Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", operationId=" + operationId + ", accountingDate="
				+ accountingDate + ", valueDate=" + valueDate + ", type=" + type + ", amount=" + amount + ", currency="
				+ currency + ", description=" + description + "]";
	}	
	
}
