package com.cqshop.simulator.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 19/11/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddProductToCart {
    @NonNull
    private Long productId;

    @NonNull
    private Integer quantity;

}
