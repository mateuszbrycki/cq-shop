package com.cqshop.simulator.scenario.reservationlocking.action;

import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Mateusz Brycki on 2019-02-17.
 */
@Slf4j
@AllArgsConstructor
@Component
public class AddProductToWarehouse implements Action {

    private final ProductService productService;

    @Override
    public void perform() {
        String id = UUID.randomUUID().toString();
        productService.createProduct("product-" + id);
        log.info("Added product with id " + id);
    }
}
