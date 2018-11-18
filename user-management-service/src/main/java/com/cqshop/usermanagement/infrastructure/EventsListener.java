package com.cqshop.usermanagement.infrastructure;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.usermanagement.avro.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Created by Mateusz Brycki on 11/09/2018.
 */
@Component
public class EventsListener {
    private static final Logger logger = LoggerFactory.getLogger(EventsListener.class);

    @StreamListener(EventsStreams.INPUT)
    public void handleEvent(Flux<UserCreatedEvent> input) {
        input.map(event -> new UserCreatedEvent(event.getUserId() + 10, event.getTimestamp()));
        input.subscribe(event -> logger.info("Received event: " + event.toString()));
    }

    @StreamListener(EventsStreams.OUTPUT)
    public void handleEventOut(Flux<UserCreatedEvent> input) {
        input.map(event -> new UserCreatedEvent(event.getUserId() + 10, event.getTimestamp()));
        input.subscribe(event -> logger.info("Received event: " + event.toString()));
    }
}
