package com.fabrickSB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.model.accountBalance.AccountBalanceResponse;
import com.fabrickSB.service.RestTemplateService;

@RestController
@RequiredArgsConstructor
public class AccountBalanceController {
	private final static String ACCOUNT_ID = "accountId";
	private final static String ACCOUNT_BALANCE_ENDPOINT = "/balance/{accountId}";
	private final RestTemplateService rts;
	@Value("${DOMAIN}")
    private String domain;
	@Value("${ACCOUNT_BALANCE_ENDPOINT}")
    private String accountBalanceEndpoint;

	@GetMapping(ACCOUNT_BALANCE_ENDPOINT)
	public ResponseEntity<AccountBalanceResponse> getAccountBalance(
			@PathVariable(ACCOUNT_ID) String accountId
	) throws Exception {
		
		String url = domain + accountBalanceEndpoint;
		//Per popolare %s del file application.properties
		url = String.format(url, accountId);
				
		AccountBalanceResponse abr = rts.getEntity(url, AccountBalanceResponse.class, null);
	    return ResponseEntity.ok(abr);
	}
}
