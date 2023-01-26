package com.fabrickSB.model.accountBalance;

import com.fabrickSB.model.accountBalance.AccountBalance;
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
