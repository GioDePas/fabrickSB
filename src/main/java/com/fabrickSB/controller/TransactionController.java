package com.fabrickSB.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fabrickSB.errors.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabrickSB.exceptions.BadRequestException;
import com.fabrickSB.errors.ErrorResponse;
import com.fabrickSB.errors.ErrorResponseList;
import com.fabrickSB.model.transaction.TransactionResponse;
import com.fabrickSB.service.RestTemplateService;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final RestTemplateService rts;
    @Value("${DOMAIN}")
    private String domain;
    @Value("${TRANSACTION_ENDPOINT}")
    private String transactionEndpoint;

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<TransactionResponse> getTransactions(
            @PathVariable("accountId") String accountId,
            @RequestParam String toAccountingDate,
            @RequestParam String fromAccountingDate) throws Exception {

        //Blocco di validazione input della data
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(toAccountingDate);
            dateFormat.parse(fromAccountingDate);
        } catch (ParseException e) {
            ErrorResponseList error = ErrorResponseList
                    .builder()
                    .status(ErrorMessages.KO_MESSAGE)
                    .errors(List.of(ErrorResponse
                            .builder()
                            .description(e.getMessage())
                            .build()))
                    .build();
            throw new BadRequestException(error);
        }

        String url = domain + transactionEndpoint;
        //Per popolare %s del file application.properties
        url = String.format(url, accountId);

        MultiValueMap<String, String> map = new LinkedMultiValueMap< >();
        map.add("toAccountingDate", toAccountingDate);
        map.add("fromAccountingDate", fromAccountingDate);

        TransactionResponse transactionResponse = rts.getEntity(url, TransactionResponse.class, map);

        return ResponseEntity.ok(transactionResponse);
    }
}