package com.fabrickSB.controller;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.model.ErrorResponse;
import com.fabrickSB.model.TransactionResponse;
import com.fabrickSB.service.HeaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TransactionController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HeaderService headerService;

	private final ObjectMapper mapper = new ObjectMapper();

	@GetMapping("/transactions/{accountId}")
	public TransactionResponse getTransactions(
			@PathVariable("accountId") String accountId, 
			@RequestParam String toAccountingDate, 
			@RequestParam String fromAccountingDate) throws JsonMappingException, JsonProcessingException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // impostiamo su false per non permettere date non valide

		try {
		    dateFormat.parse(toAccountingDate);
		    dateFormat.parse(fromAccountingDate);
		} catch (ParseException e) {
			return new ErrorResponse("Invalid date format");
		}
		
		String domain = "https://sandbox.platfr.io";
	    String endpoint = "/api/gbs/banking/v4.0/accounts/{accountId}/transactions";
	    URI uri = UriComponentsBuilder.fromUriString(domain)
	            .path(endpoint)
	            .queryParam("toAccountingDate", toAccountingDate)
	            .queryParam("fromAccountingDate", fromAccountingDate)
	            .buildAndExpand(accountId)
	            .toUri();
	    
	    //System.out.println(uri);
	    
	    Map<String, String> params = new HashMap<>();
	    params.put("toAccountingDate", toAccountingDate);
	    params.put("fromAccountingDate", fromAccountingDate);

	    
	    //HttpHeaders headers = new HttpHeaders();
	    //headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
	    //headers.set("Auth-Schema", "S2S");
	    //headers.set("Content-Type", "application/json");
	    
	    HttpEntity<String> entity = new HttpEntity<>(headerService.getHeaders());
	    
	    String response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
	    
	    //System.out.println(response);
	    TransactionResponse transactions = mapper.readValue(response, TransactionResponse.class);
	    
	    //System.out.println(transactions);
		
		return transactions;
	}
	
}