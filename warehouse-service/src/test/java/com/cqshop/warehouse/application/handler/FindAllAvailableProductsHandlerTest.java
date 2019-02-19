package com.cqshop.warehouse.application.handler;

import com.cqshop.warehouse.application.query.FindAllAvailableProducts;
import com.cqshop.warehouse.domain.repository.ProductRepository;
import com.cqshop.warehouse.dto.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindAllAvailableProductsHandlerTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private FindAllAvailableProductsHandler handler;

    @Test
    public void shouldReturnEmptyListOfProducts_noProductsInDB() {

        //given
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        //when
        List<Product> products = handler.handle(new FindAllAvailableProducts());

        //then
        assertTrue(products.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListOfProducts_noAvailableProductsInDB() {

        //given
        when(productRepository.findAll()).thenReturn(Arrays.asList(
                com.cqshop.warehouse.domain.Product.builder().quantity(0).build(),
                com.cqshop.warehouse.domain.Product.builder().quantity(0).build()
        ));

        //when
        List<Product> products = handler.handle(new FindAllAvailableProducts());

        //then
        assertTrue(products.isEmpty());
    }

    @Test
    public void shouldReturnOneProduct() {

        //given
        when(productRepository.findAll()).thenReturn(Arrays.asList(
                com.cqshop.warehouse.domain.Product.builder().quantity(1).build(),
                com.cqshop.warehouse.domain.Product.builder().quantity(0).build()
        ));

        //when
        List<Product> products = handler.handle(new FindAllAvailableProducts());

        //then
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
    }

    @Test
    public void shouldReturnTwoProducts() {

        //given
        when(productRepository.findAll()).thenReturn(Arrays.asList(
                com.cqshop.warehouse.domain.Product.builder().quantity(1).build(),
                com.cqshop.warehouse.domain.Product.builder().quantity(100).build()
        ));

        //when
        List<Product> products = handler.handle(new FindAllAvailableProducts());

        //then
        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
    }
}