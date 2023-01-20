package com.fabrickSB.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	@Value("${DOMAIN}")
    private String domain;

    @Value("${ACCOUNT_BALANCE_ENDPOINT}")
    private String accountBalanceEndpoint;

    @Value("${TRANSACTION_ENDPOINT}")
    private String transactionEndpoint;

    @Value("${MONEY_TRANSFER_ENDPOINT}")
    private String moneyTransferEndpoint;

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${AUTH_SCHEMA}")
    private String authSchema;

    @Value("${CONTENT_TYPE}")
    private String contentType;

    @Value("${X_TIME_ZONE}")
    private String xTimeZone;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAccountBalanceEndpoint() {
		return accountBalanceEndpoint;
	}

	public void setAccountBalanceEndpoint(String accountBalanceEndpoint) {
		this.accountBalanceEndpoint = accountBalanceEndpoint;
	}

	public String getTransactionEndpoint() {
		return transactionEndpoint;
	}

	public void setTransactionEndpoint(String transactionEndpoint) {
		this.transactionEndpoint = transactionEndpoint;
	}

	public String getMoneyTransferEndpoint() {
		return moneyTransferEndpoint;
	}

	public void setMoneyTransferEndpoint(String moneyTransferEndpoint) {
		this.moneyTransferEndpoint = moneyTransferEndpoint;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getAuthSchema() {
		return authSchema;
	}

	public void setAuthSchema(String authSchema) {
		this.authSchema = authSchema;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getxTimeZone() {
		return xTimeZone;
	}

	public void setxTimeZone(String xTimeZone) {
		this.xTimeZone = xTimeZone;
	}
    
}
