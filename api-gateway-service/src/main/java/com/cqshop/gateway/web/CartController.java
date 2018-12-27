package com.cqshop.gateway.web;

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

    @GetMapping("/product/{productId}")
    public HttpStatus activateAccount(@PathVariable("productId") Long productId,
                                      @RequestParam(value = "quantity") Integer quantity) {
        return cartRepository.addProductToCart(productId, quantity);
    }
}
