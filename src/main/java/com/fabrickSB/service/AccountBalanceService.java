package com.fabrickSB.service;

import com.fabrickSB.config.ApiConfig;
import com.fabrickSB.model.AccountBalance;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface AccountBalanceService {
	
	@Headers({
    	"Content-Type: application/json",
    	"Auth-Schema: S2S",
    	"Api-Key: " + ApiConfig.API_KEY
    	})
    @GET("/api/gbs/banking/v4.0/accounts/14537780/balance")
    //dichiariamo un metodo getAccountBalance che prende come parametro una stringa accountId e restituisce un oggetto Call di tipo AccountBalance
    Call<AccountBalance> getAccountBalance(@Path("accountId") String accountId);
	
}
