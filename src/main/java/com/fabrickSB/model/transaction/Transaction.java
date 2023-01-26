package com.fabrickSB.model.transaction;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
	private String transactionId;
    private String operationId;
    private Date accountingDate;
    private Date valueDate;
    private Type type;
    private Number amount;
    private String currency;
    private String description;
}
