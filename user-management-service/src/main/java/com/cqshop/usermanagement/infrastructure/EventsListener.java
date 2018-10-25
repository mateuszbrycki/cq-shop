package com.cqshop.usermanagement.infrastructure;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.usermanagement.avro.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventsListener {
    private static final Logger logger = LoggerFactory.getLogger(EventsListener.class);

    @KafkaListener(topics = "user-management-events")
    public void listen(@Payload UserCreatedEvent command) {
        logger.info("Received userCreatedEvent " + command);
    }

}
