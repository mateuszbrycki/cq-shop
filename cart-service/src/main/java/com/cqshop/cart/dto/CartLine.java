package com.cqshop.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by Mateusz Brycki on 2019-02-16.
 */
@AllArgsConstructor
@Getter
@Builder
public class CartLine {
    private Long cartLineId;

    private Long productId;

    private Double price;

    private Integer quantity;


    public static CartLine of(com.cqshop.cart.domain.CartLine cartLine) {

        return CartLine.builder()
                .cartLineId(cartLine.getCartLineId())
                .productId(cartLine.getProductId())
                .price(cartLine.getPrice())
                .quantity(cartLine.getQuantity())
                .build();
    }
}
