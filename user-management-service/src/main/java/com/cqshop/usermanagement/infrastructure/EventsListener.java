package com.cqshop.usermanagement.infrastructure;

import com.cqshop.usermanagement.domain.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 11/09/2018.
 */
@Component
public class EventsListener {

    private static final Logger logger = LoggerFactory.getLogger(EventsListener.class);

    @StreamListener(EventsStreams.INPUT)
    public void handleEvent(@Payload UserCreatedEvent event) {
        logger.error("EVENT:" + event.toString());
    }
}
