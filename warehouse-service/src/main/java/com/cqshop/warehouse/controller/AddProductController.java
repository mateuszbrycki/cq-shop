package com.cqshop.warehouse.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.warehouse.application.command.ProductAdditionRequested;
import com.cqshop.warehouse.dto.AddProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class AddProductController {

    private final Gate gate;

    @PostMapping
    public HttpStatus createUser(@RequestBody AddProduct addProduct) {
        Object result = gate.dispatch(
                ProductAdditionRequested.builder()
                        .name(addProduct.getName())
                        .code(addProduct.getCode())
                        .description(addProduct.getDescription())
                        .quantity(addProduct.getQuantity())
                        .build()
        );

        return HttpStatus.CREATED;
    }

}
