package com.cqshop.usermanagement.infrastructure;

import com.cqshop.avro.AvroMessageBuilder;
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

        /*com.cqshop.usermanagement.avro.UserCreatedEvent userCreatedEvent = new com.cqshop.usermanagement.avro.UserCreatedEvent();
        userCreatedEvent.setTimestamp(System.currentTimeMillis());
        userCreatedEvent.setUserId(((UserCreatedEvent)event).getUserId());

        messageChannel.send(messageBuilder.buildMessage(userCreatedEvent));*/
    }
}
