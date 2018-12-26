package com.cqshop.logging.cart;

import com.cqshop.cart.avro.UserCartCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Slf4j
@Component
public class CartEventsListener {

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='UserCartCreated'")
    public void handleUserCartCreated(UserCartCreated event) {
        log.info("Received UserCartCreated event " + event.toString());
    }
}
