package com.cqshop.logging;

import com.cqshop.cqrs.common.command.ApplicationCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 12/10/2018.
 */
@Slf4j
@Component
public class ApplicationCommandReceiver {
    @KafkaListener(topics = "${cq-common.application-command-topic}")
    public void listen(ApplicationCommand command) {
        log.info("Received application command " + command);
    }
}

