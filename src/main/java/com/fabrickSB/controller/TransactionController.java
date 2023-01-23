package com.fabrickSB.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.exception.BadRequestException;
import com.fabrickSB.model.ErrorResponse;
import com.fabrickSB.model.ErrorResponseList;
import com.fabrickSB.model.TransactionResponse;
import com.fabrickSB.service.RestTemplateService;

@RestController
public class TransactionController {
	
	@Autowired
	private RestTemplateService rts;
	
	@Value("${DOMAIN}")
    private String domain;
	
	@Value("${TRANSACTION_ENDPOINT}")
    private String transactionEndpoint;
	

	@GetMapping("/transactions/{accountId}")
	public ResponseEntity<TransactionResponse> getTransactions(
			@PathVariable("accountId") String accountId, 
			@RequestParam String toAccountingDate, 
			@RequestParam String fromAccountingDate) throws Exception {
		
		//Blocco di validazione input della data
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {	
		    dateFormat.parse(toAccountingDate);
		    dateFormat.parse(fromAccountingDate);
		} catch (ParseException e) {
			ErrorResponseList error = new ErrorResponseList("KO", List.of(new ErrorResponse("", e.getMessage(), "")));
			throw new BadRequestException(error);			
		}
		
		String url = domain + transactionEndpoint;
		//Per popolare %s del file application.properties
		url = String.format(url, accountId);
				
		String UrlQuery = url + "?fromAccountingDate=" + fromAccountingDate + "&toAccountingDate=" + toAccountingDate;
				
	    TransactionResponse transaction = rts.getEntity(UrlQuery, TransactionResponse.class, fromAccountingDate, toAccountingDate);
	    	    
	    return ResponseEntity.ok(transaction);
	    
	}
	
}