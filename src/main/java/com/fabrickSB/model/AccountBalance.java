package com.fabrickSB.model;

import java.util.Date;

public class AccountBalance {

	private String accountId;
	private Date date;
	private Number balance;
	private Number availableBalance;
	private String currency;
	
	public AccountBalance(String string) {
		this.accountId = string;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Number getBalance() {
		return balance;
	}

	public void setBalance(Number balance) {
		this.balance = balance;
	}

	public Number getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Number availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
