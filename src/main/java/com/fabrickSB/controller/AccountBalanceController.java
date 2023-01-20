package com.fabrickSB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.model.AccountBalanceResponse;
import com.fabrickSB.model.AppProperties;
import com.fabrickSB.service.RestTemplateService;

@RestController
public class AccountBalanceController {
	
	@Autowired
	private RestTemplateService rts;
	
	@Autowired
	private AppProperties prop;
		
	@GetMapping("/balance/{accountId}")
	public ResponseEntity<AccountBalanceResponse> getAccountBalance(@PathVariable("accountId") String accountId) throws Exception {
		
		AccountBalanceResponse accountBalance = rts.getEntity(prop.getAccountBalanceEndpoint(), accountId, AccountBalanceResponse.class, null); 
		
	    return ResponseEntity.ok(accountBalance);
		
	}
	
}
