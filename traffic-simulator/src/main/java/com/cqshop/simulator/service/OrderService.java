package com.cqshop.simulator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Component
public class OrderService extends Service {

    @Value("${cqshop.gateway.url}")
    private  String gatewayUrl;

    public void submitOrder(String username) {
        HttpEntity<Object> requestObject = getAuthRequestObject(username, null);
        template.exchange(gatewayUrl + "/order", HttpMethod.POST,requestObject, Void.class);
    }

}
