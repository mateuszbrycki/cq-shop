package com.cqshop.logging.cart;

import com.cqshop.cart.avro.CartLineUpdated;
import com.cqshop.cart.avro.ProductAddedToCart;
import com.cqshop.cart.avro.ProductRemovedFromCart;
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

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='CartLineUpdated'")
    public void handleCartLineUpdated(CartLineUpdated event) {
        log.info("Received CartLineUpdated event " + event.toString());
    }

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='ProductAddedToCart'")
    public void handleProductAddedToCart(ProductAddedToCart event) {
        log.info("Received ProductAddedToCart event " + event.toString());
    }

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='ProductRemovedFromCart'")
    public void handleProductRemovedFromCart(ProductRemovedFromCart event) {
        log.info("Received ProductRemovedFromCart event " + event.toString());
    }
}
