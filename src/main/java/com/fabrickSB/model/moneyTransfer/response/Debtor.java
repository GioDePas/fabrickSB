package com.fabrickSB.model.moneyTransfer.response;

import com.fabrickSB.model.moneyTransfer.request.Account;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Debtor {
    private String name;
    private Account account;
}
