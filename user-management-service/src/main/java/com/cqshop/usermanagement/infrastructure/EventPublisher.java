package com.cqshop.usermanagement.infrastructure;

import com.cqshop.usermanagement.domain.event.UserCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class EventPublisher {

    @Qualifier("userManagementKafkaTemplate")
    private final KafkaTemplate<String, Event> template;

    @Value("${user-management-service.topic}")
    private String userManagementOutTopic;

    public void publish(Event event) {

        template.send(userManagementOutTopic, event);
        log.info("Event sent " + event);
    }
}
