package com.fabrickSB.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrickSB.exceptions.BadRequestException;
import com.fabrickSB.exceptions.ForbiddenException;
import com.fabrickSB.errors.ErrorResponseList;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class RestTemplateService {
    private final RestTemplate restTemplate;
    private final HeaderService headerService;
    private final ObjectMapper mapper;

    public <RESPONSE> RESPONSE getEntity(String url, Class<RESPONSE> response, MultiValueMap<String, String> map) throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(headerService.getHeaders());

        try {
            return restTemplate.exchange(UriComponentsBuilder.fromUriString(url).queryParams(map).toUriString(), HttpMethod.GET, entity, response).getBody();

        } catch (HttpClientErrorException e) {
            //Se sbaglio account == FORBIDDEN 403
            if (e.getStatusCode().value() == HttpStatus.FORBIDDEN.value()) {
                ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
                throw new ForbiddenException(error);
            }
            //BAD REQUEST 400
            ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
            throw new BadRequestException(error);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public <REQUEST, RESPONSE> RESPONSE postEntity(String url, Class<RESPONSE> responseBody, REQUEST requestBody) throws Exception {

        HttpEntity<REQUEST> entity = new HttpEntity<>(requestBody, headerService.getHeaders());

        try {
            return restTemplate.exchange(url, HttpMethod.POST, entity, responseBody).getBody();

        } catch (HttpClientErrorException e) {
            //Se sbaglio account == FORBIDDEN 403
            if (e.getStatusCode().value() == HttpStatus.FORBIDDEN.value()) { //403
                ErrorResponseList error = mapper.readValue(e.getResponseBodyAsString(Charset.defaultCharset()), ErrorResponseList.class);
                throw new ForbiddenException(error);
            }
            //BAD REQUEST 400
            InputStream is = new ByteArrayInputStream(e.getResponseBodyAsString().getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ErrorResponseList error = mapper.readValue(br.lines().collect(Collectors.joining("")), ErrorResponseList.class);
            throw new BadRequestException(error);
            //GENERIC 5xx
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
