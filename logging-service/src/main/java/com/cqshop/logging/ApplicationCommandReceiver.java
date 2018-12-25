package com.cqshop.logging;


import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.notification.application.command.SendActivationLink;
import com.cqshop.usermanagement.application.command.AccountDetailsProvided;
import com.cqshop.usermanagement.avro.UserAccountCreated;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 12/10/2018.
 */
@Slf4j
@Component
public class ApplicationCommandReceiver {

    @KafkaListener(topics = "${cq-common.application-command-topic}")
    public void listen(ConsumerRecord command) {
        log.info("Received application command " + command.value().toString());
    }


/*    @KafkaListener(topics = "${cq-common.application-command-topic}")
    public void listen(SendActivationLink command) {
        log.info("Received application command SendActivationLink " + command);
    }*/
}

