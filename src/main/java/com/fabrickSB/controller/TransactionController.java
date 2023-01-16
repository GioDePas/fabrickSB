package com.fabrickSB.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.api.TransactionApi;
import com.fabrickSB.model.Transactions;

import retrofit2.Response;

@RestController
public class TransactionController {

	@GetMapping("/transactions/{accountId}")
    public Transactions getTransactions(@PathVariable String accountId,
                                        @RequestParam("fromAccountingDate") String fromAccountingDate,
                                        @RequestParam("toAccountingDate") String toAccountingDate) {
        try {
            //chiamo il metodo della classe TransactionAPI per effettuare la chiamata all'API
            Response<Transactions> response = TransactionApi.getTransactions(accountId, fromAccountingDate, toAccountingDate).execute();

            //se la chiamata ha avuto successo
            if(response.isSuccessful()) {
                //ritorno la lista delle transazioni
                return response.body();
            } else {
                //altrimenti ritorno un messaggio di errore
                return new Transactions("Error: " + response.message());
            }
        } catch (IOException e) {
            //gestione degli errori
            return new Transactions("Error: " + e.getMessage());
        }
    }
	
}
