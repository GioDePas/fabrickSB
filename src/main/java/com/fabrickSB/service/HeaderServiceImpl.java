package com.fabrickSB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.fabrickSB.model.AppProperties;

@Service
public class HeaderServiceImpl implements HeaderService{
	@Autowired
	private AppProperties prop;
	
	@Override
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Api-Key", prop.getApiKey());
        headers.set("Auth-Schema", prop.getAuthSchema());
        headers.set("Content-Type", prop.getContentType());
        headers.set("X-Time-Zone", prop.getXTimeZone());
		return headers;
	}
}
