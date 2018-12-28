package com.cqshop.warehouse.domain.event;

import com.cqshop.kafka.event.Event;
import lombok.*;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationMaterialized extends Event {

    private Long reservationId;
    private Long productId;
    private Long userId;
    private Integer quantity;
    private Long materializationDate;

}
