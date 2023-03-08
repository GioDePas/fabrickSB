package com.fabrickSB.model.moneyTransfer.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    @Size(message = "40 characters limit", max = 40)
    private String address;
    private String city;
    private String countryCode;
}
