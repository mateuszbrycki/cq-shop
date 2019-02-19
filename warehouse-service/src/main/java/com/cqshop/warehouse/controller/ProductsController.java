package com.cqshop.warehouse.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.warehouse.application.query.FindAllAvailableProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-16.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final Gate gate;

    @GetMapping
    public List<com.cqshop.warehouse.dto.Product> listAllProducts() {
        return gate.dispatch(new FindAllAvailableProducts());
    }
}
