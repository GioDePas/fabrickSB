package com.fabrickSB.model.moneyTransfer.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account {
    @NotNull(message = "Account code mandatory")
    private String accountCode;
    private String bicCode;
}
