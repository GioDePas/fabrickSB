package com.fabrickSB.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class HeaderServiceImpl implements HeaderService{

	@Override
	public HttpHeaders getHeaders() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        headers.set("Auth-Schema", "S2S");
        headers.set("Content-Type", "application/json");
		return headers;
		
	}

}
