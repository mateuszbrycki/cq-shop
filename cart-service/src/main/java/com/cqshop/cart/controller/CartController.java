package com.cqshop.cart.controller;

import com.cqshop.cart.application.query.FindUserCart;
import com.cqshop.cart.dto.Cart;
import com.cqshop.cqrs.common.gate.Gate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final Gate gate;

    @GetMapping("user/{userId}")
    public ResponseEntity<Cart> getUserCart(@PathVariable("userId") long userId) {

        Optional<Cart> userCart = gate.dispatch(new FindUserCart(userId));

        return userCart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

}
