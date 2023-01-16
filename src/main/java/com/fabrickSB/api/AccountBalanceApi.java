package com.fabrickSB.api;

import com.fabrickSB.config.ApiConfig;
import com.fabrickSB.model.AccountBalance;
import com.fabrickSB.service.AccountBalanceService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountBalanceApi {

	//creo un oggetto Retrofit per effettuare la chiamata all'API
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //creo un oggetto del servizio (interfaccia) per effettuare la chiamata
    private static AccountBalanceService service = retrofit.create(AccountBalanceService.class);

    //metodo di tipo per effettuare la chiamata all'API e ottenere il saldo
    public static Call<AccountBalance> getAccountBalance(String accountId) {
        //effettuo la chiamata al metodo del servizio passando come parametro l'accountId
        return service.getAccountBalance(accountId);
    }
	
}
