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

import com.fabrickSB.controller.AccountBalanceController;
import com.fabrickSB.model.accountBalance.AccountBalanceResponse;
import com.fabrickSB.service.RestTemplateService;

@ActiveProfiles("test")
@WebMvcTest(AccountBalanceController.class)
public class AccountBalanceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestTemplateService rts;
    private String balanceMapping = "/balance/123";
    @Value("${DOMAIN}")
    private String domain;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        when(rts.getEntity(domain + "/balance", AccountBalanceResponse.class, null)).thenReturn(new AccountBalanceResponse());
        this.mockMvc.perform(get(balanceMapping).contentType("application/json")).andExpect(status().isOk());
    }
}