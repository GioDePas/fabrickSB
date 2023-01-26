package com.fabrickSB.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
}
