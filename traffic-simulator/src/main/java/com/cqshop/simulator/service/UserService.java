package com.cqshop.simulator.service;

import com.cqshop.simulator.dto.RegisterAccountRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
}
