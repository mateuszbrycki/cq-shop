package com.cqshop.cart.controller;

import com.cqshop.cart.domain.service.CartEntityService;
import com.cqshop.cart.dto.Cart;
import com.cqshop.cart.dto.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartEntityService cartService;

    @GetMapping("user/{userId}")
    public ResponseEntity<Cart> getUserCart(@PathVariable("userId") long userId) {

        Optional<com.cqshop.cart.domain.Cart> userCart = cartService.getUserCart(userId);

        return userCart.map(cart -> ResponseEntity.ok(convertToDTO(cart)))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    private Cart convertToDTO(com.cqshop.cart.domain.Cart userCart) {

        List<Product> products = userCart.getCartLines().stream()
                .map(line -> {
                    return Product.builder()
                            .productId(line.getProductId())
                            .price(line.getPrice())
                            .quantity(line.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());


        return Cart.builder()
                .products(products)
                .cartId(userCart.getCartId())
                .cartOwner(userCart.getCartOwner())
                .build();
    }

}
