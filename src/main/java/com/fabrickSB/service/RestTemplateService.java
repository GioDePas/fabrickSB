package com.fabrickSB.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

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
	
	public <REQUEST, RESPONSE> RESPONSE postEntity(String url, Class<RESPONSE> responseBody, REQUEST requestBody) throws Exception {
		
		HttpEntity<REQUEST> entity = new HttpEntity<REQUEST>(requestBody, headerService.getHeaders());
		
        try {      	
            return restTemplate.exchange(url, HttpMethod.POST, entity, responseBody).getBody();
            
        } catch (HttpClientErrorException e) {     
        	//Se sbaglio account
			if (e.getStatusCode().value() == HttpStatus.FORBIDDEN.value()) {		
				
				ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
				throw new ForbiddenException(error);		
			}
			//BADREQUEST
			InputStream is = new ByteArrayInputStream(e.getResponseBodyAsString().getBytes());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
        	ErrorResponseList error =  mapper.readValue(br.lines().collect(Collectors.joining("")), ErrorResponseList.class);
	    	throw new BadRequestException(error); 
	    	//GENERICA
        } catch(Exception e) {
	    	throw new Exception(e.getMessage());  	    	
        }         
	}
	
}
