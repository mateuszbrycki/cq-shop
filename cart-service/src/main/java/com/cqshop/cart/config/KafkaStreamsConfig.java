package com.cqshop.cart.config;

import com.cqshop.cart.infrastructure.EventsStreams;
import com.cqshop.cart.infrastructure.listener.order.OrderEventsStreams;
import com.cqshop.cart.infrastructure.listener.usermanagement.UserManagementEventsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */
@EnableBinding({EventsStreams.class, UserManagementEventsStreams.class, OrderEventsStreams.class})
public class KafkaStreamsConfig {
}
