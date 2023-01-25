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
import org.springframework.web.client.RestTemplate;

import com.fabrickSB.model.AccountBalanceResponse;
import com.fabrickSB.model.TransactionResponse;
import com.fabrickSB.service.HeaderService;
import com.fabrickSB.service.RestTemplateService;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class GetRestTemplateTest {

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
          .when(restTemplate.exchange(domain + "/balance", HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), AccountBalanceResponse.class))
          .thenReturn(abEntity);
        
        AccountBalanceResponse testedAcr = rts.getEntity(domain + "/balance", AccountBalanceResponse.class);
        Assertions.assertEquals(abr, testedAcr);
        
    }
    
    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject_TR() throws Exception {
        
        TransactionResponse tr = new TransactionResponse();
        
        ResponseEntity<TransactionResponse> trEntity = ResponseEntity.ok(tr);
        
        Mockito
          .when(restTemplate.exchange(domain + "/transactions", HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), TransactionResponse.class))
          .thenReturn(trEntity);
        
        TransactionResponse testedTr = rts.getEntity(domain + "/transactions", TransactionResponse.class);
        Assertions.assertEquals(tr, testedTr);
        
   }
    
}
