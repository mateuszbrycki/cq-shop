package com.cqshop.logging.cart;

import com.cqshop.cart.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Slf4j
@Component
public class CartEventsListener {

    private BufferedWriter writer;

    public CartEventsListener() throws IOException {
        prepareProductAddedToCartLogger();
    }

    private void prepareProductAddedToCartLogger() throws IOException {
        writer = new BufferedWriter(new FileWriter("../logs/ProductAddedToCart.csv", true));
        writer.write("cart-id, product-id, price, quantity, class\n");
    }

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='UserCartCreated'")
    public void handleUserCartCreated(UserCartCreated event) {
        log.info("Received UserCartCreated event " + event.toString());
    }

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='CartLineUpdated'")
    public void handleCartLineUpdated(CartLineUpdated event) {
        log.info("Received CartLineUpdated event " + event.toString());
    }

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='CartLineRemoved'")
    public void handleCartLineRemoved(CartLineRemoved event) {
        log.info("Received CartLineRemoved event " + event.toString());
    }

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='ProductAddedToCart'")
    public void handleProductAddedToCart(ProductAddedToCart event) {
/*        StringBuilder builder = new StringBuilder()
                .append(event.getCartId())
                .append(",")
                .append(event.getProductId())
                .append(",")
                .append(event.getPrice())
                .append(",")
                .append(event.getQuantity())
                .append(",")
                .append(event.getTimestamp())
                .append(",")
                .append(event.getQuantity() > 10 ? "1" : "0")
                .append("\n");*/

        StringBuilder builder = new StringBuilder()
                .append(event.getCartId())
                .append(",")
                .append(event.getProductId())
                .append(",")
                .append(event.getPrice())
                .append(",")
                .append(event.getQuantity())
                .append(",")
                .append(event.getQuantity() > 50 && (event.getPrice()/event.getQuantity()) < 1 ? "1" : "0")
                .append("\n");
                    //class

        try {
            writer.write(builder.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("Received ProductAddedToCart event " + event.toString());
    }

    @StreamListener(target = CartStreamsConfig.CART_EVENTS, condition = "headers['event-type']=='ProductRemovedFromCart'")
    public void handleProductRemovedFromCart(ProductRemovedFromCart event) {
        log.info("Received ProductRemovedFromCart event " + event.toString());
    }
}
