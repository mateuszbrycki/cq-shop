package com.cqshop.notification.config;

import com.cqshop.notification.infrastructure.EventsStreams;
import com.cqshop.notification.infrastructure.order.OrderServiceEvents;
import com.cqshop.notification.infrastructure.usermanagement.UserManagementEvents;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */
@EnableBinding(value = {EventsStreams.class, UserManagementEvents.class, OrderServiceEvents.class})
public class KafkaStreamsConfig {
}
