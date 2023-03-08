package com.fabrickSB;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fabrickSB.controller.MoneyTransferController;
import com.fabrickSB.model.moneyTransfer.request.MoneyTransfer;
import com.fabrickSB.model.moneyTransfer.response.MoneyTransferPayload;
import com.fabrickSB.service.RestTemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(MoneyTransferController.class)
public class MoneyTransferControllerTest {
	private static final String MONEY_TRANSFERS = "/money-transfers";
	private static final String FAKE_ENDPOINT = "/123";
	private static final String FILE_NAME = "testRequest.json";
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private RestTemplateService rts;
	@Value("${DOMAIN}")
    private String domain;
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {

		File file = new ClassPathResource(FILE_NAME).getFile();

    	MoneyTransfer mt = mapper.readValue(file ,MoneyTransfer.class);
    	    	
		when(rts.postEntity(domain + MONEY_TRANSFERS, MoneyTransferPayload.class, mt))
		.thenReturn(new MoneyTransferPayload());
		String moneyTransfersMapping = MONEY_TRANSFERS + FAKE_ENDPOINT;
		this.mockMvc.perform(MockMvcRequestBuilders.
				post(moneyTransfersMapping)
				.content(mapper.writeValueAsString(mt))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
