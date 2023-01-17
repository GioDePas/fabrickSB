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
        URI uri = UriComponentsBuilder.fromUriString(domain)
                .path(endpoint)
                .buildAndExpand(accountId)
                .toUri();
        
        //System.out.println(uri);
        
        //HttpHeaders headers = new HttpHeaders();
        //headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        //headers.set("Auth-Schema", "S2S");
        //headers.set("Content-Type", "application/json");
        
        HttpEntity<String> entity = new HttpEntity<String>(headerService.getHeaders());
        
        //System.out.println(headers);
        
        //System.out.println(entity);
        
        String response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
        
        //System.out.println(response);
        AccountBalanceResponse accountBalance = mapper.readValue(response, AccountBalanceResponse.class);
        
       // AccountBalanceResponse response = restTemplate.exchange(uri, HttpMethod.GET, entity, AccountBalanceResponse.class).getBody();
        
		return accountBalance;
		
	}
	
}
