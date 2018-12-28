package com.cqshop.warehouse.dto;

import lombok.*;

/**
 * Created by Mateusz Brycki on 19/11/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateReservation {
    @NonNull
    private Long productId;

    @NonNull
    private Long userId;

    @NonNull
    private Integer quantity;

}
