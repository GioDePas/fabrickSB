package com.fabrickSB;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.controller.TransactionController;
import com.fabrickSB.model.transaction.TransactionResponse;
import com.fabrickSB.service.RestTemplateService;

@ActiveProfiles("test")
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    private static final String TO_ACCOUNTING_DATE = "toAccountingDate";
    private static final String FROM_ACCOUNTING_DATE = "fromAccountingDate";
    private static final String DATE_TO = "2020-01-01";
    private static final String DATE_FROM = "2019-01-01";
    private static final String CONTENT_TYPE = "application/json";
    private static final String TRANSACTION = "/transactions";
    private static final String FAKE_ENDPOINT = "/123";
    private static final String QUERY = "?" + FROM_ACCOUNTING_DATE + "=" + DATE_FROM + "&" + TO_ACCOUNTING_DATE + "=" + DATE_TO;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestTemplateService rts;
    @Value("${DOMAIN}")
    private String domain;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(TO_ACCOUNTING_DATE, DATE_TO);
        map.add(FROM_ACCOUNTING_DATE, DATE_FROM);

        when(rts.getEntity(
                UriComponentsBuilder.fromUriString(domain + TRANSACTION).queryParams(map).toUriString(),
                TransactionResponse.class, map)).thenReturn(new TransactionResponse());
        this.mockMvc.perform(get(
                TRANSACTION + FAKE_ENDPOINT + QUERY).contentType(CONTENT_TYPE)).andExpect(status().isOk());
    }
}
