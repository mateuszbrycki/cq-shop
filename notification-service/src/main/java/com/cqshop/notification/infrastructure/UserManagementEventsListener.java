package com.cqshop.notification.infrastructure;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.notification.application.command.SendActivationLink;
import com.cqshop.notification.avro.ActivationLinkSent;
import com.cqshop.usermanagement.avro.UserAccountCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Created by Mateusz Brycki on 25/11/2018.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserManagementEventsListener {

    private final Gate gate;

    @StreamListener(UserManagementEvents.INPUT)
    public void userManagementEvents(Flux<UserAccountCreated> input) {
        input.subscribe(event -> {
            log.info("Received event UserAccountCreated: " + event.toString());
            gate.dispatch(SendActivationLink.builder()
                    .email(event.getEmail())
                    .username(event.getUsername())
                    .build()
            );
        });
    }
}
