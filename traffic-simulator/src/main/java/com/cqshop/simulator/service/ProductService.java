package com.cqshop.simulator.service;

import com.cqshop.simulator.dto.AddProductToWarehouse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Component
public class ProductService extends Service {

    @Value("${cqshop.warehouse.url}")
    private String warehouseUrl;

    public void createProduct(String productName) {

        AddProductToWarehouse request = AddProductToWarehouse.builder()
                .code(productName)
                .description(productName)
                .name(productName)
                .quantity(new Random().nextInt())
                .build();

        template.postForObject(warehouseUrl + "/api/product", request, Void.class);
    }
}
