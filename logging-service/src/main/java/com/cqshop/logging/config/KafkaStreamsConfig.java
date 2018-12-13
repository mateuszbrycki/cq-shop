package com.cqshop.logging.config;

import com.cqshop.logging.notification.NotificationServiceStreamsConfig;
import com.cqshop.logging.usermanagement.UserManagementStreamsConfig;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */
@EnableBinding({
        UserManagementStreamsConfig.class,
        NotificationServiceStreamsConfig.class
})
public class KafkaStreamsConfig {
}
