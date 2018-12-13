package com.cqshop.notification.infrastructure;

import com.cqshop.avro.AvroMessageBuilder;
import com.cqshop.notification.domain.event.ActivationLinkSent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class EventPublisher {

    private final NotificationStreams eventsStreams;

    private final AvroMessageBuilder messageBuilder;

    public void publish(Event event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();

        com.cqshop.notification.avro.ActivationLinkSent activationLinkSent = new com.cqshop.notification.avro.ActivationLinkSent();
        activationLinkSent.setEmail(((ActivationLinkSent)event).getEmail());
        activationLinkSent.setUsername(((ActivationLinkSent)event).getUsername());
        activationLinkSent.setTimestamp(System.currentTimeMillis());


        messageChannel.send(messageBuilder.buildMessage(activationLinkSent));
    }
}
