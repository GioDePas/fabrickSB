package com.fabrickSB.model.moneyTransfer.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Creditor {
    @NotNull(message = "Creditor name obbligatorio")
    @Size(max = 70, message = "Superato limite 70 caratteri")
    private String name;
    @Valid
    private Account account;
    @Valid
    private Address address;
}
