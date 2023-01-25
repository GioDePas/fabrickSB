package com.fabrickSB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.model.AccountBalanceResponse;
import com.fabrickSB.service.RestTemplateService;

@RestController
public class AccountBalanceController {
	
	@Autowired
	private RestTemplateService rts;
	
	@Value("${DOMAIN}")
    private String domain;
	
	@Value("${ACCOUNT_BALANCE_ENDPOINT}")
    private String accountBalanceEndpoint;
	
	@GetMapping("/balance/{accountId}")
	public ResponseEntity<AccountBalanceResponse> getAccountBalance(
			@PathVariable("accountId") String accountId) throws Exception {
		
		String url = domain + accountBalanceEndpoint;
		//Per popolare %s del file application.properties
		url = String.format(url, accountId);
				
		AccountBalanceResponse accountBalanceResponse = rts.getEntity(url, AccountBalanceResponse.class, null); 
		
	    return ResponseEntity.ok(accountBalanceResponse);		
	}
	
}
