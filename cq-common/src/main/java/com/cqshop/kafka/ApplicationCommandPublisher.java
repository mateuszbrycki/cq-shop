package com.cqshop.kafka;

import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.cqrs.common.query.ApplicationQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 12/10/2018.
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationCommandPublisher {

    private final KafkaTemplate<String, ApplicationCommand> applicationCommandTemplate;
    private final KafkaTemplate<String, ApplicationQuery> applicationQueryTemplate;

    @Value("${cq-common.application-command-topic}")
    private String applicationCommandTopic;

    public void publish(ApplicationCommand command) {

        applicationCommandTemplate.send(applicationCommandTopic, command);
        log.info("Application command published  " + command);
    }

    public void publish(ApplicationQuery query) {

        applicationQueryTemplate.send(applicationCommandTopic, query);
        log.info("Application query published  " + query);
    }
}
