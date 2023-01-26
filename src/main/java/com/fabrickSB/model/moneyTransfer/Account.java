package com.fabrickSB.model.moneyTransfer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account {
    @NotNull(message = "AccountCode obbligatorio")
    private String accountCode;
    private String bicCode;
}
