package com.cqshop.usermanagement.infrastructure;

import com.cqshop.usermanagement.domain.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Slf4j
@Component
public class EventPublisher {


    private final KafkaTemplate<String, GenericRecord> template;

    public EventPublisher(@Qualifier("kafka-template") KafkaTemplate<String, GenericRecord> template) {
        this.template = template;
    }


    public void publish(Event event) {

        com.cqshop.usermanagement.avro.UserCreatedEvent userCreatedEvent = new com.cqshop.usermanagement.avro.UserCreatedEvent();
        userCreatedEvent.setTimestamp(System.currentTimeMillis());
        userCreatedEvent.setUserId(((UserCreatedEvent)event).getUserId());

        template.send("user-management-events", userCreatedEvent);
        template.send("user-management-events-log", userCreatedEvent);
    }
}
