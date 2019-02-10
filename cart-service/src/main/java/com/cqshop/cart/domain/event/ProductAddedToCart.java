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
public class ProductAddedToCart extends Event {

    private long productId;
    private int quantity;
    private double price;
    private long cartId;
}
