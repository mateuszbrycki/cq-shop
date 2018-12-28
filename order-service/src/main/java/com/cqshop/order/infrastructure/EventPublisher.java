package com.cqshop.order.infrastructure;

import com.cqshop.avro.AvroMessageBuilder;
import com.cqshop.kafka.event.Event;
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
        try {
            Class avroClass = Class.forName("com.cqshop.order.avro." + event.getClass().getSimpleName());
            Object convertedEvent = conversionService.convert(event, avroClass);
            publishEvent(convertedEvent);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    private void publishEvent(Object event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();

        log.info("Publishing " + event.getClass() + " " + event);
        messageChannel.send(messageBuilder.buildMessage(event));
    }

}
