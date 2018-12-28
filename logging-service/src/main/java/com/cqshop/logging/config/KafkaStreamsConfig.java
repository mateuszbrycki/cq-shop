package com.cqshop.logging.config;

import com.cqshop.logging.cart.CartStreamsConfig;
import com.cqshop.logging.notification.NotificationServiceStreamsConfig;
import com.cqshop.logging.order.OrderStreamsConfig;
import com.cqshop.logging.usermanagement.UserManagementStreamsConfig;
import com.cqshop.logging.warehouse.WarehouseStreamsConfig;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */
@EnableBinding({
        UserManagementStreamsConfig.class,
        NotificationServiceStreamsConfig.class,
        CartStreamsConfig.class,
        WarehouseStreamsConfig.class,
        OrderStreamsConfig.class
})
public class KafkaStreamsConfig {
}
