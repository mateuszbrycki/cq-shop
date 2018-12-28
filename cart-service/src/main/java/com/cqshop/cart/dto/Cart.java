package com.cqshop.cart.dto;

import lombok.*;

import java.util.List;

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

}
