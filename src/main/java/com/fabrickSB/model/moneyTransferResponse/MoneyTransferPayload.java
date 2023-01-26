package com.fabrickSB.model.moneyTransferResponse;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MoneyTransferPayload {
    private String status;
    private MoneyTransferResponse payload;
    private List<Object> error;
}
