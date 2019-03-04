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

    public Product addProduct(Product newProduct, int quantity) {

        Product existingProduct = productRepository.findByCode(newProduct.getCode());

        if (existingProduct != null) {
            updateProduct(existingProduct, quantity);

            return existingProduct;
        } else {
            saveNewProduct(newProduct, quantity);

            return newProduct;
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
                .price(product.getPrice())
                .build());
    }

    private void updateProduct(Product existingProduct, int quantity) {
        quantity += existingProduct.getQuantity();
        existingProduct.setQuantity(quantity);
        productRepository.save(existingProduct);

        eventPublisher.publish(ProductUpdatedInWarehouse.builder()
                .code(existingProduct.getCode())
                .name(existingProduct.getName())
                .id(existingProduct.getProductId())
                .quantity(existingProduct.getQuantity())
                .price(existingProduct.getPrice())
                .build());

    }
}
