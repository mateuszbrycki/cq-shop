package com.cqshop.cart.controller;

import com.cqshop.cart.domain.Cart;
import com.cqshop.cart.domain.exception.CartNotFoundException;
import com.cqshop.cart.domain.repository.CartRepository;
import com.cqshop.cart.dto.CartLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-16.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart/lines")
public class CartLinesController {

    private final CartRepository cartRepository;

    @GetMapping
    public List<CartLine> listAllProductsInCart(@RequestHeader("X-User-Id") Long userId) throws CartNotFoundException {
        Cart cart = cartRepository.findByCartOwner(userId)
                .orElseThrow(CartNotFoundException::new);

        return cart.getCartLines()
                .stream()
                .map(CartLine::of)
                .collect(Collectors.toList());
    }
}
