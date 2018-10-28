package com.cqshop.kafka;

import com.cqshop.cqrs.common.command.ApplicationCommand;
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

    private final KafkaTemplate<String, ApplicationCommand> template;

    @Value("${cq-common.application-command-topic}")
    private String applicationCommandTopic;

    public void publish(ApplicationCommand command) {

        template.send(applicationCommandTopic, command);
        log.info("Application command published  " + command);
    }
}
