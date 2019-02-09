package com.cqshop.simulator.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
public abstract class Service {

    protected final RestTemplate template = new RestTemplate();


    protected HttpEntity<Object> getAuthRequestObject(int userId, Object request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("user-" + userId, "user-" + userId);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }

}
