package com.cqshop.notification.infrastructure.order;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
public interface OrderServiceEvents {
    String INPUT = "order-events-in";

    @Input(INPUT)
    SubscribableChannel inboundEvents();
}
