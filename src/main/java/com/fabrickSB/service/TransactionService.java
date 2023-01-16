package com.fabrickSB.service;

import com.fabrickSB.config.ApiConfig;
import com.fabrickSB.model.Transactions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TransactionService {

	@Headers({
        "Content-Type: application/json",
        "Auth-Schema: S2S",
        "Api-Key: " + ApiConfig.API_KEY
    })
    @GET("/api/gbs/banking/v4.0/accounts/14537780/transactions")
    Call<Transactions> getTransactions(@Path("accountId") String accountId,
                                      @Query("fromAccountingDate") String fromAccountingDate,
                                      @Query("toAccountingDate") String toAccountingDate);
	
}