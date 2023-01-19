package com.fabrickSB.model.moneyTransfer;

import jakarta.validation.constraints.Size;

public class Address {

	@Size(message = "Superato limite 40 caratteri")
	private String address;
    private String city;
    private String countryCode;
    
	public Address() {
	
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", city=" + city + ", countryCode=" + countryCode + "]";
	}
    
}
