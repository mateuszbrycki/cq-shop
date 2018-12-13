package com.cqshop.avro;

import org.springframework.context.ApplicationEvent;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

/**
 * Created by Mateusz Brycki on 17/10/2018.
 */
@Component
public class AvroMessageBuilder {

    public Message buildMessage(Object event) {
        return MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeType.valueOf(AvroMimeType.AVRO_MIME_TYPE))
                .setHeader("event-type", event.getClass().getSimpleName())
                .build();
    }

}
