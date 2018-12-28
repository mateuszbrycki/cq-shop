package com.cqshop.cart.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {

    private long productId;
    private int quantity;
    private double price;
}
