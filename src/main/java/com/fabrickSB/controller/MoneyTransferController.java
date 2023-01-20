package com.fabrickSB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.model.AppProperties;
import com.fabrickSB.model.moneyTransfer.MoneyTransfer;
import com.fabrickSB.model.moneyTransferResponse.MoneyTransferPayload;
import com.fabrickSB.service.RestTemplateService;

import jakarta.validation.Valid;

@RestController
public class MoneyTransferController {

	@Autowired
	private RestTemplateService rts;
	
	@Autowired
	private AppProperties prop;
		
	@PostMapping("/money-transfers/{accountId}")
	public ResponseEntity<MoneyTransferPayload> postMoneyTransfer(@PathVariable("accountId") String accountId, @Valid @RequestBody MoneyTransfer moneyTransfer) throws Exception {
		
		MoneyTransferPayload moneyTransferPayload = rts.postEntity(prop.getMoneyTransferEndpoint(), accountId, MoneyTransferPayload.class, moneyTransfer);
		
		return ResponseEntity.ok(moneyTransferPayload);
				
	}
	
}
