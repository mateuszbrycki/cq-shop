package com.cqshop.warehouse.controller;

import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-03-02.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/{productId}")
    public ResponseEntity<com.cqshop.warehouse.dto.Product> findProductById(@PathVariable("productId") Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        return product.map(e -> ResponseEntity.ok(com.cqshop.warehouse.dto.Product.of(e)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
