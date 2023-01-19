package com.fabrickSB.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.model.AccountBalanceResponse;
import com.fabrickSB.service.HeaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AccountBalanceController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HeaderService headerService;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/balance/{accountId}")
	public AccountBalanceResponse getAccountBalance(@PathVariable("accountId") String accountId) throws JsonMappingException, JsonProcessingException {
		
		String domain = "https://sandbox.platfr.io";
        String endpoint = "/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        URI uri = UriComponentsBuilder
        		.fromUriString(domain)
                .path(endpoint)
                .buildAndExpand(accountId)
                .toUri();
        
        HttpEntity<String> entity = new HttpEntity<String>(headerService.getHeaders());
        
        String response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
        
        AccountBalanceResponse accountBalance = mapper.readValue(response, AccountBalanceResponse.class);
        
		return accountBalance;
		
	}
	
}
