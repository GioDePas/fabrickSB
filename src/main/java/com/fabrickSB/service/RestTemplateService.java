package com.fabrickSB.service;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fabrickSB.exception.BadRequestException;
import com.fabrickSB.exception.ForbiddenException;
import com.fabrickSB.model.ErrorResponseList;
import com.fasterxml.jackson.databind.ObjectMapper;


//generics types per get e post
@Service
public class RestTemplateService {
	
	@Autowired 
	private RestTemplate restTemplate;
	
	@Autowired
	private HeaderService headerService;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	public <REQUEST> REQUEST getEntity(String url, Class<REQUEST> requestBody, String ... requestParams) throws Exception {
		
		HttpEntity<String> entity = new HttpEntity<String>(headerService.getHeaders());
        		
        try {
        	
            return restTemplate.exchange(url, HttpMethod.GET, entity, requestBody).getBody();
            
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
	
	public <REQUEST, RESPONSE> REQUEST postEntity(String url, Class<REQUEST> requestBody, RESPONSE responseBody) throws Exception {
		
		HttpEntity<RESPONSE> entity = new HttpEntity<RESPONSE>(responseBody, headerService.getHeaders());
		
        try {      	
            return restTemplate.exchange(url, HttpMethod.POST, entity, requestBody).getBody();
            
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
