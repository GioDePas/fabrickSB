package com.fabrickSB.model.moneyTransfer.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    @Size(message = "Superato limite 40 caratteri")
    private String address;
    private String city;
    private String countryCode;
}
