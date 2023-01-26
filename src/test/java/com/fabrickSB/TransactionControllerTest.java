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
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestTemplateService rts;
    private String transactionsMapping = "/transactions/123?fromAccountingDate=2019-01-01&toAccountingDate=2020-01-01";
    @Value("${DOMAIN}")
    private String domain;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("toAccountingDate", "2020-01-01");
        map.add("fromAccountingDate", "2019-01-01");

        when(rts.getEntity(UriComponentsBuilder.fromUriString(domain + "/transactions").queryParams(map).toUriString(), TransactionResponse.class, map)).thenReturn(new TransactionResponse());
        this.mockMvc.perform(get(transactionsMapping).contentType("application/json")).andExpect(status().isOk());
    }
}
