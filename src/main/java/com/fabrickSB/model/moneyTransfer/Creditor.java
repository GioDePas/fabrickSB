package com.fabrickSB.model.moneyTransfer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Creditor {

	@NotNull(message = "Creditor name obbligatorio")
	@Size(max = 70, message = "Superato limite 70 caratteri")
    private String name;
	@Valid
	private Account account;
	@Valid
    private Address address;
    
	public Creditor() {}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Creditor [name=" + name + ", account=" + account + ", address=" + address + "]";
	}
    
}
