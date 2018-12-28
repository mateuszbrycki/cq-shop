package com.cqshop.cart.infrastructure.listener.usermanagement;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
public interface UserManagementEventsStreams {
    String INPUT = "user-management-events-in";

    @Input(INPUT)
    SubscribableChannel inboundEvents();
}