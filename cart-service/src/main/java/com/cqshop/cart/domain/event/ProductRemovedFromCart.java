package com.cqshop.cart.domain.event;

import com.cqshop.kafka.event.Event;
import lombok.*;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductRemovedFromCart extends Event {

    private long productId;
    private long cartLineId;
    private long cartId;
}
