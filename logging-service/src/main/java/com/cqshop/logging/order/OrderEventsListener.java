package com.cqshop.logging.order;

import com.cqshop.cart.avro.UserCartCreated;
import com.cqshop.order.avro.OrderCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Slf4j
@Component
public class OrderEventsListener {

    @StreamListener(target = OrderStreamsConfig.ORDER_EVENTS, condition = "headers['event-type']=='OrderCreated'")
    public void handleOrderCreated(OrderCreated event) {
        log.info("Received OrderCreated event " + event.toString());
    }

}
