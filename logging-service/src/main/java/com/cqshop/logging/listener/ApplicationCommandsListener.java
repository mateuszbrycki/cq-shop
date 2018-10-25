package com.cqshop.logging.listener;

import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.logging.Entity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 25/10/2018.
 */
@Component
public class ApplicationCommandsListener {

    @KafkaListener(topics = "${cq-common.application-command-topic}")
    public void listenWithHeaders(ApplicationCommand message) {
        System.out.println("Received Application Command: " + message);
    }
}
