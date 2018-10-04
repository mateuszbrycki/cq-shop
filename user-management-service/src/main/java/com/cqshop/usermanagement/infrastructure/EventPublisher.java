package com.cqshop.usermanagement.infrastructure;

import com.cqshop.usermanagement.domain.event.UserCreatedEvent;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Component
public class EventPublisher {

    private final EventsStreams eventsStreams;

    public EventPublisher(EventsStreams eventsStreams) {
        this.eventsStreams = eventsStreams;
    }

    public void publish(UserCreatedEvent event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();

        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
