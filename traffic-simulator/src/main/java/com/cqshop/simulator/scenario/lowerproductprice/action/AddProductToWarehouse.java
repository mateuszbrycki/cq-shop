package com.cqshop.simulator.scenario.lowerproductprice.action;

import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Mateusz Brycki on 2019-02-17.
 */
@Profile("lowerProductPriceScenario")
@Slf4j
@AllArgsConstructor
@Component
public class AddProductToWarehouse implements Action {

    private final ProductService productService;

    private static int COUNTER = 0;

    @Override
    public void perform() {
        String id = UUID.randomUUID().toString();

        if (COUNTER % 20 == 0) {
            productService.createProduct("product-" + id, (Math.random() * ((1 - 0) + 1)) + 0);
            log.info("Added product with id " + id + " and lower price");
        } else {
            productService.createProduct("product-" + id);
            log.info("Added product with id " + id);
        }
        COUNTER++;
    }
}
