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

import com.fabrickSB.controller.TransactionController;
import com.fabrickSB.model.TransactionResponse;
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
		
		when(rts.getEntity(domain + "/transactions", TransactionResponse.class, "2019-01-01", "2020-01-01")).thenReturn(new TransactionResponse());
		this.mockMvc.perform(get(transactionsMapping).contentType("application/json")).andExpect(status().isOk());
		
	}
	
}
