package com.cqshop.simulator.service;

import com.cqshop.simulator.dto.AddProductToCart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Component
public class CartService extends Service {

    @Value("${cqshop.gateway.url}")
    private String gatewayUrl;

    public void addProductToCart(int userId, int productId, int productAmount) {

        AddProductToCart request = AddProductToCart.builder()
                .productId((long)productId)
                .quantity(productAmount)
                .build();

        String url = gatewayUrl + "/cart/product";

        HttpEntity<Object> requestObj = getAuthRequestObject(userId, request);
        template.exchange(url, HttpMethod.POST,requestObj, Void.class);
    }

    public void removeProductFromCart(int userId, int productId, int productAmount) {
        String url = gatewayUrl + "/cart/product/" + productId;

        HttpEntity<Object> requestObj = getAuthRequestObject(userId, null);
        template.exchange(url, HttpMethod.DELETE,requestObj, Void.class);
    }

}
