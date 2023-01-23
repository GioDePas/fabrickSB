package com.fabrickSB.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${AUTH_SCHEMA}")
    private String authSchema;

    @Value("${CONTENT_TYPE}")
    private String contentType;

    @Value("${X_TIME_ZONE}")
    private String xTimeZone;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getAuthSchema() {
		return authSchema;
	}

	public void setAuthSchema(String authSchema) {
		this.authSchema = authSchema;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getxTimeZone() {
		return xTimeZone;
	}

	public void setxTimeZone(String xTimeZone) {
		this.xTimeZone = xTimeZone;
	}
    
}
