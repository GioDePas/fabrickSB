package com.fabrickSB.model.moneyTransfer;

import com.fabrickSB.enums.BeneficiaryType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TaxRelief {
    private String taxReliefId;
    @NotNull(message = "isCondoUpgrade obbligatorio")
    private Boolean isCondoUpgrade;
    @NotNull(message = "creditorFiscalCode obbligatorio")
    private String creditorFiscalCode;
    private BeneficiaryType beneficiaryType;
    @Valid
    private NaturalPersonBeneficiary naturalPersonBeneficiary;
    @Valid
    private LegalPersonBeneficiary legalPersonBeneficiary;
}
