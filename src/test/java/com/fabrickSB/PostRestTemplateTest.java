package com.fabrickSB;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.fabrickSB.model.moneyTransfer.MoneyTransfer;
import com.fabrickSB.model.moneyTransferResponse.MoneyTransferPayload;
import com.fabrickSB.service.HeaderService;
import com.fabrickSB.service.RestTemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PostRestTemplateTest {
	@Mock
	private RestTemplate restTemplate;
    @Mock
    private HeaderService headerService;
    @InjectMocks
    private RestTemplateService rts;
    private final ObjectMapper mapper = new ObjectMapper();
	private String fileName = "testRequest.json";
	@Value("${DOMAIN}")
    private String domain;
    @BeforeEach
    void init() {
    	Mockito
          .when(headerService.getHeaders())
          .thenReturn(new HttpHeaders());
    }
    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() throws Exception {
    	File file = new ClassPathResource(fileName).getFile();
    	
        MoneyTransfer mt = mapper.readValue(file ,MoneyTransfer.class);

    	MoneyTransferPayload mtp = new MoneyTransferPayload();
    	
    	ResponseEntity<MoneyTransferPayload> entity = ResponseEntity.ok(mtp);
    	
    	Mockito
    		.when(restTemplate.exchange(domain + "/money-transfers", HttpMethod.POST, new HttpEntity<MoneyTransfer>(mt, new HttpHeaders()), MoneyTransferPayload.class))
    		.thenReturn(entity);
    	
		MoneyTransferPayload testedMtp = rts.postEntity(domain + "/money-transfers", MoneyTransferPayload.class, mt);
    	Assertions.assertEquals(mtp, testedMtp);
    }
}
