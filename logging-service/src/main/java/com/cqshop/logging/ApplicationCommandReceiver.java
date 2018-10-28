package com.cqshop.logging;


import com.cqshop.usermanagement.application.command.RegisterAccountCommand;
import com.cqshop.usermanagement.avro.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Created by Mateusz Brycki on 12/10/2018.
 */
@Slf4j
@Component
public class ApplicationCommandReceiver {

    @KafkaListener(topics = "${cq-common.application-command-topic}")
    public void listen(RegisterAccountCommand command) {
        log.info("Received application command " + command);
    }

    @KafkaListener(topics = "user-management-topic")
    public void listen(UserCreatedEvent command) {
        log.info("Received userCreatedEvent " + command);
    }


}

