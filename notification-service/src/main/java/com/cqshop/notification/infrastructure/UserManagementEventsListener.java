package com.cqshop.notification.infrastructure;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.notification.application.command.SendActivationLink;
import com.cqshop.notification.avro.ActivationLinkSent;
import com.cqshop.usermanagement.avro.GenericUserManagementEvent;
import com.cqshop.usermanagement.avro.UserAccountCreated;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import com.cqshop.kafka.event.EventIdBuilder;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by Mateusz Brycki on 25/11/2018.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserManagementEventsListener {

    private final Gate gate;
    private final EventIdBuilder eventIdBuilder;


    @StreamListener(target = UserManagementEvents.INPUT, condition = "headers['event-type']=='UserAccountCreated'")
    public void userManagementEvents(UserAccountCreated event) {

            log.info("Received " + event);

            Consumer<UserAccountCreated> handleUserCreatedEvent = (userAccountCreatedEvent) -> {
                log.info("Received event UserAccountCreated: " + event.toString());
                gate.dispatch(SendActivationLink.builder()
                        .email(userAccountCreatedEvent.getEmail())
                        .username(userAccountCreatedEvent.getUsername())
                        .build()
                );
            };

            handleUserCreatedEvent.accept(event);
    }
}
