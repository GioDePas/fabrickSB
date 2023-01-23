package com.fabrickSB;
/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.fabrickSB.model.AccountBalanceResponse;
import com.fabrickSB.service.HeaderService;
import com.fabrickSB.service.RestTemplateService;

@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AccountBalanceTest {

	@Spy
	private RestTemplate restTemplate = new RestTemplate();
	    
	    @Mock
	    private HeaderService headerService;

	    @InjectMocks
	    private RestTemplateService rts;

	    @Test
	    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() throws Exception {
	
	    	Mockito
	          .when(headerService.getHeaders())
	          .thenReturn(new HttpHeaders());

	    	
	        AccountBalanceResponse acr = new AccountBalanceResponse ();
	        
	        ResponseEntity<AccountBalanceResponse> entity = new ResponseEntity<AccountBalanceResponse>(HttpStatus.OK);
	        
	        Mockito
	          .when(restTemplate.exchange("/test", HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), AccountBalanceResponse.class))
	          .thenReturn(entity);
	        
	        AccountBalanceResponse testedAcr = rts.getEntity("/test", "123", null, null);
	        Assertions.assertEquals(acr, testedAcr);
	        
	        
	   }
}
*/	

