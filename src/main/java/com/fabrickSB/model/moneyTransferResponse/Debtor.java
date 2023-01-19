package com.fabrickSB.model.moneyTransferResponse;

import com.fabrickSB.model.moneyTransfer.Account;

public class Debtor {
	
	private String name;
    private Account account;
    
	public Debtor(String name, Account account) {
		this.name = name;
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Debtor [name=" + name + ", account=" + account + "]";
	}
    
}
