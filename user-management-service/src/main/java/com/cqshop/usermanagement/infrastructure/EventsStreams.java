package com.cqshop.usermanagement.infrastructure;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

public interface EventsStreams {
    String INPUT = "user-management-events-in";
    String OUTPUT = "user-management-events-out";

    @Input(INPUT)
    SubscribableChannel inboundEvents();

    @Output(OUTPUT)
    MessageChannel outboundEvents();
}
