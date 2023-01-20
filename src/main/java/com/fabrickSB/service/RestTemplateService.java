package com.fabrickSB.service;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.exception.BadRequestException;
import com.fabrickSB.exception.ForbiddenException;
import com.fabrickSB.model.ErrorResponseList;
import com.fabrickSB.model.moneyTransfer.MoneyTransfer;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestTemplateService {

	@Value("${DOMAIN}")
    private String domain;
	
	@Autowired 
	private RestTemplate restTemplate;
	
	@Autowired
	private HeaderService headerService;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public <T> T getEntity(String endpoint, String accountId, Class<T> c, MultiValueMap<String, String> requestParam) throws Exception {
		
		String uri = UriComponentsBuilder
        		.fromUriString(domain)
                .path(endpoint)
                .queryParams(requestParam)
                .buildAndExpand(accountId)
                .toUriString();
		
		HttpEntity<String> entity = new HttpEntity<String>(headerService.getHeaders());
        		
        try {   	
            return restTemplate.exchange(uri, HttpMethod.GET, entity, c).getBody();
            
        } catch (HttpClientErrorException e) {    
        	//Se sbaglio account
			if (e.getStatusCode().value() == HttpStatus.FORBIDDEN.value()) {
				ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
				throw new ForbiddenException(error);
			}			
        	//BADREQUEST
        	ErrorResponseList error =  mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
	    	throw new BadRequestException(error);
		
        } catch(Exception e) {   	
	    	throw new Exception(e.getMessage());  	    	
        }        
	}
	
	public <T> T postEntity(String endpoint, String accountId, Class<T> c, MoneyTransfer moneyTransfer) throws Exception {
		
		String uri = UriComponentsBuilder
        		.fromUriString(domain)
                .path(endpoint)
                .buildAndExpand(accountId)
                .toUriString();
		
		HttpEntity<MoneyTransfer> entity = new HttpEntity<MoneyTransfer>(moneyTransfer, headerService.getHeaders());
		
        try {      	
            return restTemplate.exchange(uri, HttpMethod.POST, entity, c).getBody();
            
        } catch (HttpClientErrorException e) {     
        	//Se sbaglio account
			if (e.getStatusCode().value() == HttpStatus.FORBIDDEN.value()) {				
				ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
				throw new ForbiddenException(error);		
			}			
        	//BADREQUEST
        	ErrorResponseList error =  mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
	    	throw new BadRequestException(error);
	    	
        } catch(Exception e) {
	    	throw new Exception(e.getMessage());  	    	
        }         
	}
	
}
