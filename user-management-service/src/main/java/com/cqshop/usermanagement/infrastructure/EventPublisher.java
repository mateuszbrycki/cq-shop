package com.cqshop.usermanagement.infrastructure;

import com.cqshop.avro.AvroMessageBuilder;
import com.cqshop.usermanagement.avro.GenericUserManagementEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 01/10/2018.
 */
@Slf4j
@Component
public class EventPublisher {

    private final EventsStreams eventsStreams;

    private final AvroMessageBuilder messageBuilder;

    private final ConversionService conversionService;

    public EventPublisher(EventsStreams eventsStreams,
                          AvroMessageBuilder messageBuilder,
                          @Qualifier("mvcConversionService") ConversionService conversionService) {
        this.eventsStreams = eventsStreams;
        this.messageBuilder = messageBuilder;
        this.conversionService = conversionService;
    }

    public void publish(Event event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();

        GenericUserManagementEvent convertedDomainEvent = conversionService.convert(event, GenericUserManagementEvent.class);

        log.info("Publishing " + event.getClass() + " " + convertedDomainEvent);
        messageChannel.send(messageBuilder.buildMessage(convertedDomainEvent));
    }

}
