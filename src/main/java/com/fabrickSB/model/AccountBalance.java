package com.fabrickSB.model;

import java.util.Date;

public class AccountBalance {

	private Date date;
	private Number balance;
	private Number availableBalance;
	private String currency;

	public AccountBalance() {
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

	@Override
	public String toString() {
		return "AccountBalance [date=" + date + ", balance=" + balance + ", availableBalance="
				+ availableBalance + ", currency=" + currency + "]";
	}
	
}
