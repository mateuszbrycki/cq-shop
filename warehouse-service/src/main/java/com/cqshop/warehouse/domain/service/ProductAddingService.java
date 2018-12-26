package com.cqshop.warehouse.domain.service;

import com.cqshop.kafka.event.Event;
import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.event.ProductAddedToWarehouse;
import com.cqshop.warehouse.domain.event.ProductUpdatedInWarehouse;
import com.cqshop.warehouse.domain.repository.ProductRepository;
import com.cqshop.warehouse.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@RequiredArgsConstructor
@Component
public class ProductAddingService {

    private final ProductRepository productRepository;
    private final EventPublisher eventPublisher;

    public Product addProduct(Product product, int quantity) {

        Product existingProduct = productRepository.findByCode(product.getCode());

        if (existingProduct != null) {
            updateProduct(existingProduct, quantity);

            return existingProduct;
        } else {
            saveNewProduct(product, quantity);

            return product;
        }
    }

    private void saveNewProduct(Product product, int quantity) {
        product.setQuantity(quantity);
        productRepository.save(product);

        eventPublisher.publish(ProductAddedToWarehouse.builder()
                .code(product.getCode())
                .name(product.getName())
                .id(product.getProductId())
                .quantity(product.getQuantity())
                .build());
    }

    private void updateProduct(Product product, int quantity) {
        quantity += product.getQuantity();
        product.setQuantity(quantity);
        productRepository.save(product);

        eventPublisher.publish(ProductUpdatedInWarehouse.builder()
                .code(product.getCode())
                .name(product.getName())
                .id(product.getProductId())
                .quantity(product.getQuantity())
                .build());

    }
}
