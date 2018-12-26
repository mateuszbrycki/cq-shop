package com.cqshop.logging.warehouse;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface WarehouseStreamsConfig {

    String WAREHOUSE_EVENTS = "warehouse-events-in";

    @Input(WAREHOUSE_EVENTS)
    SubscribableChannel inboundEvents();
}
