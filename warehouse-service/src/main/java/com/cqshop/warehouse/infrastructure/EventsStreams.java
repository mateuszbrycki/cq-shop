package com.cqshop.warehouse.infrastructure;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
public interface EventsStreams {
    String INPUT = "warehouse-events-in";
    String OUTPUT = "warehouse-events-out";

    @Output(OUTPUT)
    MessageChannel outboundEvents();
}