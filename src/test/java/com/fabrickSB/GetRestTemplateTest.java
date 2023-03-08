package com.fabrickSB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.model.accountBalance.AccountBalanceResponse;
import com.fabrickSB.model.transaction.TransactionResponse;
import com.fabrickSB.service.HeaderService;
import com.fabrickSB.service.RestTemplateService;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class GetRestTemplateTest {
    private static final String BALANCE = "/balance";
    private static final String TRANSACTIONS = "/transactions";
    private static final String TO_ACCOUNTING_DATE = "toAccountingDate";
    private static final String FROM_ACCOUNTING_DATE = "fromAccountingDate";
    private static final String DATE_TO = "2020-01-01";
    private static final String DATE_FROM = "2019-01-01";
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private HeaderService headerService;
    @InjectMocks
    private RestTemplateService rts;
    @Value("${DOMAIN}")
    private String domain;

    @BeforeEach
    void init() {
        Mockito
                .when(headerService.getHeaders())
                .thenReturn(new HttpHeaders());
    }

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject_AB() throws Exception {

        AccountBalanceResponse abr = new AccountBalanceResponse();
        ResponseEntity<AccountBalanceResponse> abEntity = ResponseEntity.ok(abr);

        Mockito
                .when(restTemplate.exchange(
                        domain + BALANCE,
                        HttpMethod.GET,
                        new HttpEntity<String>(new HttpHeaders()), AccountBalanceResponse.class))
                .thenReturn(abEntity);

        AccountBalanceResponse testedAcr = rts.getEntity(domain + BALANCE, AccountBalanceResponse.class, null);
        Assertions.assertEquals(abr, testedAcr);
    }

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject_TR() throws Exception {
        TransactionResponse tr = new TransactionResponse();
        ResponseEntity<TransactionResponse> trEntity = ResponseEntity.ok(tr);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(TO_ACCOUNTING_DATE, DATE_TO);
        map.add(FROM_ACCOUNTING_DATE, DATE_FROM);

        Mockito.when(restTemplate.exchange(
                UriComponentsBuilder.fromUriString(domain + TRANSACTIONS).queryParams(map).toUriString(),
                        HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), TransactionResponse.class))
                .thenReturn(trEntity);

        TransactionResponse testedTr = rts.getEntity(domain + TRANSACTIONS, TransactionResponse.class, map);
        Assertions.assertEquals(tr, testedTr);
    }
}
