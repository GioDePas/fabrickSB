package com.fabrickSB.model.accountBalance;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountBalance {
    private Date date;
    private Number balance;
    private Number availableBalance;
    private String currency;
}
