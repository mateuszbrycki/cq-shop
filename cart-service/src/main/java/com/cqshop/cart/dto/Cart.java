package com.cqshop.cart.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Cart {

    private Long cartOwner;
    private Long cartId;
    private List<Product> products;

    public static Cart of(com.cqshop.cart.domain.Cart userCart) {
        List<Product> products = userCart.getCartLines().stream()
                .map(line -> {
                    return Product.builder()
                            .productId(line.getProductId())
                            .price(line.getPrice())
                            .quantity(line.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());


        return com.cqshop.cart.dto.Cart.builder()
                .products(products)
                .cartId(userCart.getCartId())
                .cartOwner(userCart.getCartOwner())
                .build();
    }

}
