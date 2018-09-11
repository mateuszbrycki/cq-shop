package com.mateuszbrycki.example2;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


/**
 * Created by Mateusz Brycki on 11/09/2018.
 */
public interface EventsStreams {

    String INPUT = "events-in";
    String OUTPUT = "events-out";

    @Input(INPUT)
    SubscribableChannel inboundEvents();

    @Output(OUTPUT)
    MessageChannel outboundEvents();
}
