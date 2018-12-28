package com.cqshop.cart.infrastructure.listener.order;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.cart.application.command.CartCreationRequested;
import com.cqshop.cart.application.command.RemoveCartLines;
import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.kafka.listener.AbstractEventListener;
import com.cqshop.order.avro.OrderCreated;
import com.cqshop.usermanagement.avro.UserAccountCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.core.convert.ConversionService;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class OrderCreatedEventListener extends AbstractEventListener {

    private final Gate gate;


    public OrderCreatedEventListener(@Qualifier("mvcConversionService") ConversionService conversionService, Gate gate) {
        super(conversionService);
        this.gate = gate;
    }

    @StreamListener(target = OrderEventsStreams.INPUT, condition = "headers['event-type']=='OrderCreated'")
    public void handleEvent(@Payload OrderCreated event) {
            log.info("Received OrderCreated" + event);

            Consumer<OrderCreated> handleOrderCreated = (convertedEvent) -> {
                gate.dispatch(
                        RemoveCartLines.builder()
                                .userId(convertedEvent.getOrderOwner())
                                .build()
                );
            };

            handleOrderCreated.accept(event);

    }

}
