package com.cqshop.notification.infrastructure.order;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.kafka.event.EventIdBuilder;
import com.cqshop.notification.application.command.SendActivationLink;
import com.cqshop.notification.application.command.SendOrderConfirmation;
import com.cqshop.notification.infrastructure.usermanagement.UserManagementEvents;
import com.cqshop.order.avro.OrderCreated;
import com.cqshop.usermanagement.avro.UserAccountCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderServiceEventsListener {

    private final Gate gate;
    private final EventIdBuilder eventIdBuilder;


    @StreamListener(target = OrderServiceEvents.INPUT, condition = "headers['event-type']=='OrderCreated'")
    public void handleOrderCreated(OrderCreated event) {

        log.info("Received " + event);

        Consumer<OrderCreated> handleUserCreatedEvent = (orderCreated) -> {
            log.info("Received event OrderCreated: " + event.toString());
            gate.dispatch(SendOrderConfirmation.builder()
                    .userId(orderCreated.getOrderOwner())
                    .orderId(orderCreated.getOrderId())
                    .build()
            );
        };

        handleUserCreatedEvent.accept(event);
    }
}
