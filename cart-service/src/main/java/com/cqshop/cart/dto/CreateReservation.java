package com.cqshop.cart.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 19/11/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateReservation {
    @NonNull
    private Long productId;

    @NonNull
    private Long userId;

    @NonNull
    private Integer quantity;

}
