package com.fabrickSB.model.accountBalance;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountBalanceResponse {
    private String status;
    private AccountBalance payload;
    private List<Object> error;
}
