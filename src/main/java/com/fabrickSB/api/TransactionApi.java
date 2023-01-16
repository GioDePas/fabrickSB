package com.fabrickSB.api;

import com.fabrickSB.config.ApiConfig;
import com.fabrickSB.model.Transactions;
import com.fabrickSB.service.TransactionService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionApi {
	
	private static Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(ApiConfig.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
            .build();

    private static TransactionService service = retrofit.create(TransactionService.class);

    public static Call<Transactions> getTransactions(String accountId, String fromAccountingDate, String toAccountingDate) {
        return service.getTransactions(accountId, fromAccountingDate, toAccountingDate);
    }
			
			;
	
}
