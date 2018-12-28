package com.cqshop.gateway.web;

import com.cqshop.gateway.dto.AddProductToCart;
import com.cqshop.gateway.repository.CartRepository;
import com.cqshop.gateway.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping
    public HttpStatus addProductToCart() {
        return orderRepository.createOrder();
    }

}
