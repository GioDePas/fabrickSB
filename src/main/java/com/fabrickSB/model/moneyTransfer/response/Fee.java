package com.fabrickSB.model.moneyTransfer.response;

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
