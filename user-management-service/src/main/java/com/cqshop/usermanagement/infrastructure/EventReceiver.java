package com.cqshop.usermanagement.infrastructure;

import com.cqshop.usermanagement.domain.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 12/10/2018.
 */
@Slf4j
@Component
public class EventReceiver {

    @KafkaListener(topics = "${user-management-service.topic}")
    public void listen(Event event) {
        log.info("Received event " + event);
    }
}
