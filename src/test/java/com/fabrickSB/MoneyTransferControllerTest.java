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
import com.fabrickSB.model.moneyTransfer.MoneyTransfer;
import com.fabrickSB.model.moneyTransferResponse.MoneyTransferPayload;
import com.fabrickSB.service.RestTemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(MoneyTransferController.class)
public class MoneyTransferControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RestTemplateService rts;
	
	private String moneyTransfersMapping = "/money-transfers/123";
	
	@Value("${DOMAIN}")
    private String domain;
	
	private String fileName = "testRequest.json";
		
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		
    	File file = new ClassPathResource(fileName).getFile();

    	MoneyTransfer mt = mapper.readValue(file ,MoneyTransfer.class);
    	    	
		when(rts.postEntity(domain + "/money-transfers", MoneyTransferPayload.class, mt))
		.thenReturn(new MoneyTransferPayload());
		this.mockMvc.perform(MockMvcRequestBuilders.
				post(moneyTransfersMapping)
				.content(mapper.writeValueAsString(mt))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
}
