package com.cqshop.warehouse.dto;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class Product {

    private Long productId;
    private String name;
    private String code;
    private String description;
    private Integer quantity;

    public static Product of(com.cqshop.warehouse.domain.Product product) {

        return Product.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .code(product.getCode())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .build();
    }
}
