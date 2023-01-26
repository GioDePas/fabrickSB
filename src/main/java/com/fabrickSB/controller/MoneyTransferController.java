package com.fabrickSB.controller;

import com.fabrickSB.model.errorsManagement.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.enums.TaxReliefId;
import com.fabrickSB.exception.BadRequestException;
import com.fabrickSB.model.errorsManagement.ErrorResponseList;
import com.fabrickSB.model.moneyTransfer.MoneyTransfer;
import com.fabrickSB.model.moneyTransferResponse.MoneyTransferPayload;
import com.fabrickSB.service.RestTemplateService;

import jakarta.validation.Valid;

@RestController
public class MoneyTransferController {

    @Autowired
    private RestTemplateService rts;

    @Value("${DOMAIN}")
    private String domain;

    @Value("${MONEY_TRANSFER_ENDPOINT}")
    private String moneyTransferEndpoint;

    @PostMapping("/money-transfers/{accountId}")
    public ResponseEntity<MoneyTransferPayload> postMoneyTransfer(
            @PathVariable("accountId") String accountId,
            @Valid @RequestBody MoneyTransfer moneyTransfer) throws Exception {

        String url = domain + moneyTransferEndpoint;
        //Per popolare %s del file application.properties
        url = String.format(url, accountId);

        //Controllo che i valori di taxRelief non siano mai uguali a null e dentro ancora che contenga i valori indicati nell'enum in caso negativo: BadRequest
        if (moneyTransfer.getTaxRelief() != null && moneyTransfer.getTaxRelief().getTaxReliefId() != null) {
            if (!TaxReliefId.containsValue(moneyTransfer.getTaxRelief().getTaxReliefId())) {
                throw new BadRequestException(ErrorResponseList.builder().status(ErrorMessages.WRONG_TAXRELIEF).build());
            }
        }

        if (!moneyTransfer.getIsInstant() && moneyTransfer.getExecutionDate() == null) {
            throw new BadRequestException(ErrorResponseList.builder().status(ErrorMessages.MANDATORY_DATA).build());
        }

        MoneyTransferPayload moneyTransferPayload = rts.postEntity(url, MoneyTransferPayload.class, moneyTransfer);

        return ResponseEntity.ok(moneyTransferPayload);
    }

}
