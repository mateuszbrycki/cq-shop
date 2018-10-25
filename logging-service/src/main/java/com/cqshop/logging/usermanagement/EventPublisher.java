package com.cqshop.logging.usermanagement;

import com.cqshop.usermanagement.avro.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 18/10/2018.
 */
@Slf4j
@Component
public class EventPublisher {

    private final KafkaTemplate<String, UserCreatedEvent> template;

    public EventPublisher(@Qualifier("userManagementKafkaTemplate") KafkaTemplate<String, UserCreatedEvent> template) {
        this.template = template;
    }

    public void publish() {
        com.cqshop.usermanagement.avro.UserCreatedEvent userCreatedEvent = new com.cqshop.usermanagement.avro.UserCreatedEvent();
        userCreatedEvent.setTimestamp(System.currentTimeMillis());
        userCreatedEvent.setUserId(99999l);

        template.send("user-management-events", userCreatedEvent);
        log.info("Application command published  " + userCreatedEvent);
    }
}
