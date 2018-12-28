package com.cqshop.order.domain.event;

import com.cqshop.kafka.event.Event;
import lombok.*;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreated extends Event {

    private long orderId;
    private long creationTime;
    private long orderOwner;
}
