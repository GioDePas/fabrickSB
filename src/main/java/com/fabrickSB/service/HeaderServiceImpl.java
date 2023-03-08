package com.fabrickSB.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.fabrickSB.model.AppProperties;

@Service
@RequiredArgsConstructor
public class HeaderServiceImpl implements HeaderService{
	private static final String API_KEY = "Api-Key";
	private static final String AUTH_SCHEMA = "Auth-Schema";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String X_TIME_ZONE = "X-Time-Zone";
	private final AppProperties prop;

	@Override
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(API_KEY, prop.getApiKey());
        headers.set(AUTH_SCHEMA, prop.getAuthSchema());
        headers.set(CONTENT_TYPE, prop.getContentType());
        headers.set(X_TIME_ZONE, prop.getXTimeZone());
		return headers;
	}
}
