package com.cqshop.simulator.service;

import com.cqshop.simulator.dto.RegisterAccountRequest;
import com.cqshop.simulator.service.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Component
public class UserService extends Service{

    @Value("${cqshop.gateway.url}")
    private String gatewayUrl;

     public void registerUser(String name) {
         RegisterAccountRequest request = RegisterAccountRequest.builder()
                 .username(name)
                 .password(name)
                 .email(name)
                 .build();

         template.postForObject(gatewayUrl + "/user", request, Void.class);

     }

     public List<User> getAllUsers() {
         HttpEntity<Object> requestObject = this.getAuthRequestObject("admin", null);
         return template.exchange(gatewayUrl + "/users", HttpMethod.GET,requestObject, new ParameterizedTypeReference<List<User>>() {}).getBody();
     }
}
