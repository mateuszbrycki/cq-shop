package com.mateuszbrycki.example2;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * Created by Mateusz Brycki on 11/09/2018.
 */
@Service
public class EventsService {

    private final EventsStreams eventsStreams;

    public EventsService(EventsStreams eventsStreams) {
        this.eventsStreams = eventsStreams;
    }

    public void sendEvent(final Event event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();

        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build()
        );
    }
}
