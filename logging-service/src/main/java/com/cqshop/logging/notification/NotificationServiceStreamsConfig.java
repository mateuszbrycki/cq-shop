package com.cqshop.logging.notification;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Mateusz Brycki on 13/12/2018.
 */
public interface NotificationServiceStreamsConfig {

    String NOTIFICATION_EVENTS = "notification-service-events-in";

    @Input(NOTIFICATION_EVENTS)
    SubscribableChannel inboundNOTEvents();
}