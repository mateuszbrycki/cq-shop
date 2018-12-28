package com.cqshop.gateway.web;

import com.cqshop.gateway.dto.AddProductToCart;
import com.cqshop.gateway.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;

    @PostMapping("/product")
    public HttpStatus addProductToCart(@RequestBody AddProductToCart addProductToCart) {
        return cartRepository.addProductToCart(addProductToCart);
    }

    @DeleteMapping("/product/{productId}")
    public HttpStatus removeProductFromCart(@PathVariable("productId") Long productId) {
        return cartRepository.removeProductFromCart(productId);
    }
}
