package com.fabrickSB.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.model.moneyTransfer.MoneyTransfer;
import com.fabrickSB.model.moneyTransferResponse.MoneyTransferPayload;
import com.fabrickSB.service.HeaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
public class MoneyTransferController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HeaderService headerService;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping("/money-transfers/{accountId}")
	public MoneyTransferPayload postMoneyTransfer(
			@PathVariable("accountId") String accountId, 
			@Valid 
			@RequestBody  MoneyTransfer moneyTransfer
			) throws JsonMappingException, JsonProcessingException {
				
		System.out.println("RequestBody = " + moneyTransfer);
		
		String domain = "https://sandbox.platfr.io";
		String endpoint = "/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
		URI uri = UriComponentsBuilder
				.fromUriString(domain)
				.path(endpoint)
				.buildAndExpand(accountId)
				.toUri();
		
		HttpEntity<MoneyTransfer> entity = new HttpEntity<MoneyTransfer>(moneyTransfer, headerService.getHeaders());
		
	    String response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class).getBody();
	    
	    System.out.println(response);
	    
	    MoneyTransferPayload moneyTransferPayload = mapper.readValue(response, MoneyTransferPayload.class);
	    
		return moneyTransferPayload;
		
	}
	
}
