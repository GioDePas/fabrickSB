package com.fabrickSB.model.moneyTransferResponse;

import com.fabrickSB.model.moneyTransfer.Account;
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
