package com.cqshop.notification.infrastructure;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationStreams {

    String INPUT = "notification-service-events-in";
    String OUTPUT = "notification-service-events-out";

    @Input(INPUT)
    SubscribableChannel inboundEvents();

    @Output(OUTPUT)
    MessageChannel outboundEvents();
}
