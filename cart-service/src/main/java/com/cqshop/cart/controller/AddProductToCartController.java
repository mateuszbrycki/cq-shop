package com.cqshop.cart.controller;

import com.cqshop.cart.application.command.AddingProductToCartRequested;
import com.cqshop.cqrs.common.gate.Gate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart/product")
public class AddProductToCartController {

    private final Gate gate;

    @GetMapping("/{productId}")
    public HttpStatus add(@PathVariable("productId") Long productId,
                          @RequestParam(value = "quantity") Integer quantity,
                          @RequestHeader("X-User-Id") Long userId) {

        Boolean result = gate.dispatch(AddingProductToCartRequested.builder()
                .productId(productId)
                .quantity(quantity)
                .userId(userId)
                .build()
        );


        if (result) {
            return HttpStatus.CREATED;
        }

        return HttpStatus.FORBIDDEN; //TODO mbrycki find better status
    }
}
