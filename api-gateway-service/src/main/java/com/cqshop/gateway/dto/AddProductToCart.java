package com.cqshop.gateway.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 19/11/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddProductToCart {
    @NonNull
    private Long productId;

    @NonNull
    private Integer quantity;

}
