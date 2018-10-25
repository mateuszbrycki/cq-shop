package com.cqshop.logging.listener;

import com.cqshop.logging.Entity;
import com.cqshop.usermanagement.avro.UserCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 25/10/2018.
 */
@Component
public class EventsListener {

    @KafkaListener(topics = "user-management-events-log")
    public void listenWithHeadersLog(Entity message) {
        System.out.println("Received: " + message);
    }

    @KafkaListener(topics = "user-management-events")
    public void listenWithHeaders(UserCreatedEvent message) {
        System.out.println("Received: " + message);
    }
}
