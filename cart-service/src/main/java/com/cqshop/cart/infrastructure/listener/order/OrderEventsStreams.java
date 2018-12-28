package com.cqshop.cart.infrastructure.listener.order;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
public interface OrderEventsStreams {
    String INPUT = "order-events-in";

    @Input(INPUT)
    SubscribableChannel inboundEvents();
}