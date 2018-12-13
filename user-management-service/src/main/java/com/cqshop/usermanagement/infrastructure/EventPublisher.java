package com.cqshop.usermanagement.infrastructure;

import com.cqshop.avro.AvroMessageBuilder;
import com.cqshop.usermanagement.domain.event.AccountActivationCodeCreated;
import com.cqshop.usermanagement.domain.event.UserAccountActivated;
import com.cqshop.usermanagement.domain.event.UserAccountCreated;
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

    public void publish(AccountActivationCodeCreated event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();

        com.cqshop.usermanagement.avro.AccountActivationCodeCreated convertedDomainEvent = conversionService.convert(event, com.cqshop.usermanagement.avro.AccountActivationCodeCreated.class);

        log.info("Publishing " + event.getClass() + " " + convertedDomainEvent);
        messageChannel.send(messageBuilder.buildMessage(convertedDomainEvent));
    }

    public void publish(UserAccountCreated event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();

        com.cqshop.usermanagement.avro.UserAccountCreated convertedDomainEvent = conversionService.convert(event, com.cqshop.usermanagement.avro.UserAccountCreated.class);

        log.info("Publishing " + event.getClass() + " " + convertedDomainEvent);
        messageChannel.send(messageBuilder.buildMessage(convertedDomainEvent));
    }

    public void publish(UserAccountActivated event) {
        MessageChannel messageChannel = eventsStreams.outboundEvents();

        com.cqshop.usermanagement.avro.UserAccountActivated convertedDomainEvent = conversionService.convert(event, com.cqshop.usermanagement.avro.UserAccountActivated.class);

        log.info("Publishing " + event.getClass() + " " + convertedDomainEvent);
        messageChannel.send(messageBuilder.buildMessage(convertedDomainEvent));
    }

}
