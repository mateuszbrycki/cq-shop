package com.cqshop.cart.controller;

import com.cqshop.cart.application.command.AddingProductToCartRequested;
import com.cqshop.cart.application.command.ProductRemovalFromCartRequested;
import com.cqshop.cart.dto.AddProductToCart;
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
public class CartContentController {

    private final Gate gate;

    @PostMapping
    public HttpStatus add(@RequestBody AddProductToCart addProductToCart,
                          @RequestHeader("X-User-Id") Long userId) {

        Boolean result = gate.dispatch(AddingProductToCartRequested.builder()
                .productId(addProductToCart.getProductId())
                .quantity(addProductToCart.getQuantity())
                .userId(userId)
                .build()
        );


        if (result) {
            return HttpStatus.CREATED;
        }

        return HttpStatus.FORBIDDEN; //TODO mbrycki find better status
    }

    @DeleteMapping("/{productId}")
    public HttpStatus remove(@PathVariable("productId") Long productId,
                          @RequestHeader("X-User-Id") Long userId) {

        Boolean result = gate.dispatch(ProductRemovalFromCartRequested.builder()
                .productId(productId)
                .userId(userId)
                .build()
        );


        if (result) {
            return HttpStatus.OK;
        }

        return HttpStatus.FORBIDDEN; //TODO mbrycki find better status
    }
}
