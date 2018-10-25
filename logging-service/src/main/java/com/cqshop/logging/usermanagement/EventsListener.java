package com.cqshop.logging.usermanagement;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.usermanagement.avro.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class EventsListener {
    private static final Logger logger = LoggerFactory.getLogger(EventsListener.class);


    @KafkaListener(topics = "user-management-events")
    public void listen(UserCreatedEvent command) {
        logger.info("Received application command " + command);
    }
}
