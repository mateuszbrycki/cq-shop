package com.cqshop.notification.infrastructure.usermanagement;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Mateusz Brycki on 25/11/2018.
 */
public interface UserManagementEvents {

    String INPUT = "user-management-events-in";

    @Input(INPUT)
    SubscribableChannel inboundEvents();
}
