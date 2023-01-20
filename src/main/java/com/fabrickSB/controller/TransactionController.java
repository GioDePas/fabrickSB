package com.fabrickSB.controller;

import java.net.URI;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.exception.BadRequestException;
import com.fabrickSB.exception.ForbiddenException;
import com.fabrickSB.model.ErrorResponse;
import com.fabrickSB.model.ErrorResponseList;
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
	public ResponseEntity<TransactionResponse> getTransactions(
			@PathVariable("accountId") String accountId, 
			@RequestParam String toAccountingDate, 
			@RequestParam String fromAccountingDate) throws JsonMappingException, JsonProcessingException {
		
		//Blocco di validazione input della data, restituisce un error message
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		
		try {
			
		    dateFormat.parse(toAccountingDate);
		    dateFormat.parse(fromAccountingDate);
		    
		} catch (ParseException e) {
			
			ErrorResponseList error = new ErrorResponseList("KO", List.of(new ErrorResponse("", e.getMessage(), "")));
			throw new BadRequestException(error);			
		}
		
		String domain = "https://sandbox.platfr.io";
	    String endpoint = "/api/gbs/banking/v4.0/accounts/{accountId}/transactions";
	    URI uri = UriComponentsBuilder.fromUriString(domain)
	            .path(endpoint)
	            .queryParam("toAccountingDate", toAccountingDate)
	            .queryParam("fromAccountingDate", fromAccountingDate)
	            .buildAndExpand(accountId)
	            .toUri();
	    
	    Map<String, String> params = new HashMap<>();
	    params.put("toAccountingDate", toAccountingDate);
	    params.put("fromAccountingDate", fromAccountingDate);

	    HttpEntity<String> entity = new HttpEntity<String>(headerService.getHeaders());
	    
	    ResponseEntity<String> response = null;
	    
	    try {
			
	    	response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	    	TransactionResponse transaction = mapper.readValue(response.getBody(), TransactionResponse.class);
	    	return ResponseEntity.ok(transaction);
	    	
		} catch (HttpClientErrorException e) {
		
			//Se sbaglio account
			if (e.getStatusCode().value() == 403) {
				
				ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
				throw new ForbiddenException(error);
			
			}
			
			ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
			throw new BadRequestException(error);
					
		} 
	    
	}
	
}