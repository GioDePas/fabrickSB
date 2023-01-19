package com.fabrickSB.model.moneyTransfer;

import jakarta.validation.constraints.NotNull;

public class Account {

	@NotNull(message = "AccountCode obbligatorio")
	private String accountCode;
	private String bicCode;
	
	public Account() {
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}

	@Override
	public String toString() {
		return "Account [accountCode=" + accountCode + ", bicCode=" + bicCode + "]";
	}
	
}
