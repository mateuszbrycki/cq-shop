package com.cqshop.simulator.service.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class Product {

    private Long productId;
    private String name;
    private String code;
    private String description;
    private Integer quantity;
    private double price;
}
