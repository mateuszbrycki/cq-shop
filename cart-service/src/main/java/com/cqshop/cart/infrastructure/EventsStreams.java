package com.cqshop.cart.infrastructure;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
public interface EventsStreams {
    String INPUT = "cart-events-in";
    String OUTPUT = "cart-events-out";
/*
    @Input(INPUT)
    SubscribableChannel inboundEvents();*/

    @Output(OUTPUT)
    MessageChannel outboundEvents();
}