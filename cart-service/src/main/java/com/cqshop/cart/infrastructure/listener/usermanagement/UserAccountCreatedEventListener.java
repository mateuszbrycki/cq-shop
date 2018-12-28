package com.cqshop.cart.infrastructure.listener.usermanagement;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.cart.application.command.CartCreationRequested;
import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.kafka.listener.AbstractEventListener;
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
public class UserAccountCreatedEventListener extends AbstractEventListener {

    private final Gate gate;


    public UserAccountCreatedEventListener(@Qualifier("mvcConversionService") ConversionService conversionService, Gate gate) {
        super(conversionService);
        this.gate = gate;
    }

    @StreamListener(target = UserManagementEventsStreams.INPUT, condition = "headers['event-type']=='UserAccountCreated'")
    public void handleEvent(@Payload UserAccountCreated event) {
            log.info("Received UserAccountCreated" + event);
            Consumer<UserAccountCreated> handleUserAccountCreatedEvent = (convertedEvent) -> {

                gate.dispatch(
                        CartCreationRequested.builder()
                                .userId(convertedEvent.getUserId())
                                .build()
                );
            };

            handleUserAccountCreatedEvent.accept(event);

    }

}
