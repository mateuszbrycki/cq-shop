package com.cqshop.warehouse.domain.service;

import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.event.ProductAddedToWarehouse;
import com.cqshop.warehouse.domain.event.ProductUpdatedInWarehouse;
import com.cqshop.warehouse.domain.repository.ProductRepository;
import com.cqshop.warehouse.infrastructure.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductAddingServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private ProductAddingService productAddingService;

    @Test
    public void shouldSaveNewProduct() {

        //given
        Product product = Product.builder()
                .code("product-CODE")
                .description("description")
                .name("name")
                .build();

        when(productRepository.findByCode("product-CODE")).thenReturn(null);

        //when
        Product newProduct = productAddingService.addProduct(product, 10);

        //then
        assertEquals(10, (int)newProduct.getQuantity());

        verify(eventPublisher).publish(ProductAddedToWarehouse.builder()
                .code(newProduct.getCode())
                .name(newProduct.getName())
                .id(newProduct.getProductId())
                .quantity(newProduct.getQuantity())
                .build());
    }

    @Test
    public void shouldUpdateProduct() {

        //given
        Product product = Product.builder()
                .code("product-CODE")
                .description("description")
                .name("name")
                .build();

        when(productRepository.findByCode("product-CODE")).thenReturn(Product.builder()
                .code("product-CODE")
                .productId(1L)
                .description("description-1")
                .name("name-1")
                .quantity(100)
                .build());

        //when
        Product newProduct = productAddingService.addProduct(product, 10);

        //then
        assertEquals(110, (int)newProduct.getQuantity());

        verify(eventPublisher).publish(ProductUpdatedInWarehouse.builder()
                .code(newProduct.getCode())
                .name(newProduct.getName())
                .id(newProduct.getProductId())
                .quantity(newProduct.getQuantity())
                .build());
    }

}