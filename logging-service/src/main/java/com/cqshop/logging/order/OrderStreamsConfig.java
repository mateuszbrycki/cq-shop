package com.cqshop.logging.order;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderStreamsConfig {

    String ORDER_EVENTS = "order-events-in";

    @Input(ORDER_EVENTS)
    SubscribableChannel inboundEvents();
}
