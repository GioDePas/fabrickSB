package com.fabrickSB.model.moneyTransfer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TaxRelief {

	private String taxReliefId;
	@NotNull(message = "isCondoUpgrade obbligatorio")
    private Boolean isCondoUpgrade;
	@NotNull(message = "creditorFiscalCode obbligatorio")
    private String creditorFiscalCode;
    private String beneficiaryType;
    @Valid
    private NaturalPersonBeneficiary naturalPersonBeneficiary;
    @Valid
    private LegalPersonBeneficiary legalPersonBeneficiary;
    
	public TaxRelief() {
		
	}

	public String getTaxReliefId() {
		return taxReliefId;
	}

	public void setTaxReliefId(String taxReliefId) {
		this.taxReliefId = taxReliefId;
	}

	public Boolean getIsCondoUpgrade() {
		return isCondoUpgrade;
	}

	public void setIsCondoUpgrade(Boolean isCondoUpgrade) {
		this.isCondoUpgrade = isCondoUpgrade;
	}

	public String getCreditorFiscalCode() {
		return creditorFiscalCode;
	}

	public void setCreditorFiscalCode(String creditorFiscalCode) {
		this.creditorFiscalCode = creditorFiscalCode;
	}

	public String getBeneficiaryType() {
		return beneficiaryType;
	}

	public void setBeneficiaryType(String beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}

	public NaturalPersonBeneficiary getNaturalPersonBeneficiary() {
		return naturalPersonBeneficiary;
	}

	public void setNaturalPersonBeneficiary(NaturalPersonBeneficiary naturalPersonBeneficiary) {
		this.naturalPersonBeneficiary = naturalPersonBeneficiary;
	}

	public LegalPersonBeneficiary getLegalPersonBeneficiary() {
		return legalPersonBeneficiary;
	}

	public void setLegalPersonBeneficiary(LegalPersonBeneficiary legalPersonBeneficiary) {
		this.legalPersonBeneficiary = legalPersonBeneficiary;
	}

	@Override
	public String toString() {
		return "TaxRelief [taxReliefId=" + taxReliefId + ", isCondoUpgrade=" + isCondoUpgrade + ", creditorFiscalCode="
				+ creditorFiscalCode + ", beneficiaryType=" + beneficiaryType + ", naturalPersonBeneficiary="
				+ naturalPersonBeneficiary + ", legalPersonBeneficiary=" + legalPersonBeneficiary + "]";
	}
	
}
