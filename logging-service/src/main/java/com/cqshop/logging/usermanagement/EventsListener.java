package com.cqshop.logging.usermanagement;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.usermanagement.avro.UserAccountCreated;
import com.cqshop.notification.avro.ActivationLinkSent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class EventsListener {

    @StreamListener(StreamsConfig.USER_MANAGEMENT_EVENTS)
    public void handleUserAccountCreated(Flux<UserAccountCreated> input) {
        input.subscribe(event -> log.info("Received event UserAccountCreated: " + event.toString()));
    }


    @StreamListener(StreamsConfig.NOTIFICATION_EVENTS)
    public void handleActivationLinkSent(Flux<ActivationLinkSent> input) {
        input.subscribe(event -> log.info("Received event ActivationLinkSent: " + event.toString()));
    }
}
