package com.cqshop.warehouse.controller;

import com.cqshop.warehouse.domain.repository.ProductRepository;
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

    private final ProductRepository productRepository;

    @GetMapping
    public List<com.cqshop.warehouse.dto.Product> listAllProducts() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getQuantity() > 0)
                .map(com.cqshop.warehouse.dto.Product::of)
                .collect(Collectors.toList());
    }
}
