package com.fabrickSB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.api.AccountBalanceApi;
import com.fabrickSB.model.AccountBalance;

import retrofit2.Response;

@RestController
public class AccountBalanceController {
	//mapping per la richiesta GET per ottenere il saldo
	@GetMapping("/balance/{accountId}")
	public AccountBalance getAccountBalance(@PathVariable String accountId) {
	    try {
	        //chiamo il metodo della classe AccountBalanceAPI per effettuare la chiamata all'API
	        Response<AccountBalance> response = AccountBalanceApi.getAccountBalance(accountId).execute();
	        
	        //se la chiamata ha avuto successo
	        if(response.isSuccessful()) {
	            //ritorno il saldo
	            return response.body();
	        } else {
	            //altrimenti ritorno un messaggio di errore
	            return new AccountBalance("Error: " + response.message());
	        }
	    } catch (Exception e) {
	        //gestione delle eccezioni
	        return new AccountBalance("Error: " + e.getMessage());
	    }
	}
}