package com.fabrickSB.controller;

import java.net.URI;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.exception.BadRequestException;
import com.fabrickSB.model.ErrorResponseList;
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
	
	ObjectMapper mapper = new ObjectMapper();
		
	@PostMapping("/money-transfers/{accountId}")
	public ResponseEntity<MoneyTransferPayload> postMoneyTransfer(
			@PathVariable("accountId") String accountId, 
			@Valid 
			@RequestBody  MoneyTransfer moneyTransfer
			) throws JsonMappingException, JsonProcessingException {
		
		String domain = "https://sandbox.platfr.io";
		String endpoint = "/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
		URI uri = UriComponentsBuilder
				.fromUriString(domain)
				.path(endpoint)
				.buildAndExpand(accountId)
				.toUri();
		
		HttpEntity<MoneyTransfer> entity = new HttpEntity<MoneyTransfer>(moneyTransfer, headerService.getHeaders());
		
	    ResponseEntity<String> response;

	    try {
	    	
	    	response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
	    	MoneyTransferPayload moneyTransferPayload = mapper.readValue(response.getBody(), MoneyTransferPayload.class);
	    	return ResponseEntity.ok(moneyTransferPayload);
			
		} catch (HttpClientErrorException e) {
			
			ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
			throw new BadRequestException(error);
			
		}
		
	}
	
}
