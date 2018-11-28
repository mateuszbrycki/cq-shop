package com.cqshop.usermanagement.infrastructure;

import com.cqshop.avro.AvroMessageBuilder;
import com.cqshop.usermanagement.domain.event.UserAccountCreated;
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

    private final EventsStreams eventsStreams;

    private final AvroMessageBuilder messageBuilder;

    public void publish(Event event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();
        //TODO mbrycki reimplement - conversion should not be done in this place
        com.cqshop.usermanagement.avro.UserAccountCreated userCreatedEvent = new com.cqshop.usermanagement.avro.UserAccountCreated();
        userCreatedEvent.setTimestamp(System.currentTimeMillis());
        userCreatedEvent.setUserId(((UserAccountCreated)event).getUserId());
        userCreatedEvent.setEmail(((UserAccountCreated)event).getEmail());
        userCreatedEvent.setUsername(((UserAccountCreated)event).getUsername());

        messageChannel.send(messageBuilder.buildMessage(userCreatedEvent));
    }
}
