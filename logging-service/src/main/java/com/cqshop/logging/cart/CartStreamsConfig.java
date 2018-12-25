package com.cqshop.logging.cart;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CartStreamsConfig {

    String CART_EVENTS = "cart-events-in";

    @Input(CART_EVENTS)
    SubscribableChannel inboundEvents();
}
