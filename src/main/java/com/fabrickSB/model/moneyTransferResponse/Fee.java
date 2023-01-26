package com.fabrickSB.model.moneyTransferResponse;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Fee {
	private String feeCode;
    private String description;
    private Number amount;
    private String currency;
}
