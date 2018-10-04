package com.cqshop.usermanagement.infrastructure;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;


/**
 * Created by Mateusz Brycki on 11/09/2018.
 */
public interface EventsStreams {

    String INPUT = "user-management-events-in";
    String OUTPUT = "user-management-events-out";

    @Input(INPUT)
    SubscribableChannel inboundEvents();

    @Output(OUTPUT)
    MessageChannel outboundEvents();
}
