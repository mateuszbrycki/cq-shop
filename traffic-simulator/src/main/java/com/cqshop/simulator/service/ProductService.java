package com.cqshop.simulator.service;

import com.cqshop.simulator.dto.AddProductToWarehouse;
import com.cqshop.simulator.service.dto.Product;
import com.cqshop.simulator.service.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Component
public class ProductService extends Service {

    @Value("${cqshop.gateway.url}")
    private  String gatewayUrl;

    public void createProduct(String productName) {

        AddProductToWarehouse request = AddProductToWarehouse.builder()
                .code(productName)
                .description(productName)
                .name(productName)
                .quantity(new Random().nextInt(2000) & Integer.MAX_VALUE)
                .price((Math.random() * ((100 - 50) + 1)) + 50)
                .build();

        performRequest(request);
    }

    public void createProduct(String productName, double price) {

        AddProductToWarehouse request = AddProductToWarehouse.builder()
                .code(productName)
                .description(productName)
                .name(productName)
                .quantity(new Random().nextInt(2000) & Integer.MAX_VALUE)
                .price(price)
                .build();

        performRequest(request);
    }

    private void performRequest(AddProductToWarehouse request) {
        HttpEntity<Object> requestObj = getAuthRequestObject("admin", request);
        template.exchange(gatewayUrl + "product/", HttpMethod.POST, requestObj, Void.class);
    }


    public List<Product> getAllProducts() {
        HttpEntity<Object> requestObject = this.getAuthRequestObject("admin", null);
        return template.exchange(gatewayUrl + "/products", HttpMethod.GET,requestObject, new ParameterizedTypeReference<List<Product>>() {}).getBody();
    }
}
