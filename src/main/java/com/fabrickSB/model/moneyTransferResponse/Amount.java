package com.fabrickSB.model.moneyTransferResponse;

import java.sql.Date;

public class Amount {

	private Number debtorAmount;
    private String debtorCurrency;
    private Number creditorAmount;
    private String creditorCurrency;
    private Date creditorCurrencyDate;
    private Number exchangeRate;
    
	public Amount() {}

	public Number getDebtorAmount() {
		return debtorAmount;
	}

	public void setDebtorAmount(Number debtorAmount) {
		this.debtorAmount = debtorAmount;
	}

	public String getDebtorCurrency() {
		return debtorCurrency;
	}

	public void setDebtorCurrency(String debtorCurrency) {
		this.debtorCurrency = debtorCurrency;
	}

	public Number getCreditorAmount() {
		return creditorAmount;
	}

	public void setCreditorAmount(Number creditorAmount) {
		this.creditorAmount = creditorAmount;
	}

	public String getCreditorCurrency() {
		return creditorCurrency;
	}

	public void setCreditorCurrency(String creditorCurrency) {
		this.creditorCurrency = creditorCurrency;
	}

	public Date getCreditorCurrencyDate() {
		return creditorCurrencyDate;
	}

	public void setCreditorCurrencyDate(Date creditorCurrencyDate) {
		this.creditorCurrencyDate = creditorCurrencyDate;
	}

	public Number getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Number exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public String toString() {
		return "Amount [debtorAmount=" + debtorAmount + ", debtorCurrency=" + debtorCurrency + ", creditorAmount="
				+ creditorAmount + ", creditorCurrency=" + creditorCurrency + ", creditorCurrencyDate="
				+ creditorCurrencyDate + ", exchangeRate=" + exchangeRate + "]";
	}
    
}
