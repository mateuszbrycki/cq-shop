package com.cqshop.notification.config;

import com.cqshop.notification.infrastructure.NotificationStreams;
import com.cqshop.notification.infrastructure.UserManagementEvents;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */
@EnableBinding(value = {NotificationStreams.class, UserManagementEvents.class})
public class KafkaStreamsConfig {
}
