package com.fabrickSB.model.moneyTransferResponse;

import java.sql.Date;
import java.util.List;

import com.fabrickSB.model.moneyTransfer.Creditor;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MoneyTransferResponse {
	private String moneyTransferId;
    private String status;
    private String direction;
    private Creditor creditor;
    private Debtor debtor;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private String createdDatetime;
    private String accountedDatetime;
    private Date debtorValueDate;
    private Date creditorValueDate;
    private Amount amount;
    private boolean isUrgent;
    private boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private List<Fee> fees;
    private boolean hasTaxRelief;
}
