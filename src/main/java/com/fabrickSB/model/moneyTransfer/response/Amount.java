package com.fabrickSB.model.moneyTransfer.response;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Amount {
	private Number debtorAmount;
    private String debtorCurrency;
    private Number creditorAmount;
    private String creditorCurrency;
    private Date creditorCurrencyDate;
    private Number exchangeRate;
}
