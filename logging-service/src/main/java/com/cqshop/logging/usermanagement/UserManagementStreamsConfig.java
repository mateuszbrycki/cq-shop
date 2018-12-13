package com.cqshop.logging.usermanagement;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserManagementStreamsConfig {

    String USER_MANAGEMENT_EVENTS = "user-management-events-in";

    @Input(USER_MANAGEMENT_EVENTS)
    SubscribableChannel inboundEvents();
}
