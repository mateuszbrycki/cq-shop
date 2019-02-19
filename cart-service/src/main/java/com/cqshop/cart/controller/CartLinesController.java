package com.cqshop.cart.controller;

import com.cqshop.cart.application.query.FindUserCartLines;
import com.cqshop.cart.domain.exception.CartNotFoundException;
import com.cqshop.cart.dto.CartLine;
import com.cqshop.cqrs.common.gate.Gate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mateusz Brycki on 2019-02-16.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart/lines")
public class CartLinesController {

    private final Gate gate;

    @GetMapping
    public List<CartLine> listAllProductsInCart(@RequestHeader("X-User-Id") Long userId) throws CartNotFoundException {

        return gate.dispatch(new FindUserCartLines(userId));

    }
}
