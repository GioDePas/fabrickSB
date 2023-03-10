package com.fabrickSB.model.moneyTransfer.request;

import com.fabrickSB.enums.FeeType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MoneyTransfer {
    @Valid
    private Creditor creditor;
    private String executionDate;
    private String uri;
    @NotNull(message = "description mandatory")
    @Size(message = "140 characters limit", max = 140)
    private String description;
    @NotNull(message = "amount mandatory")
    private Number amount;
    @NotNull(message = "currency mandatory")
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private FeeType feeType;
    private String feeAccountId;
    @Valid
    private TaxRelief taxRelief;

    //Per avere valore di default SHA
    public static class MoneyTransferBuilder {
        private FeeType feeType = FeeType.SHA;
    }
}
