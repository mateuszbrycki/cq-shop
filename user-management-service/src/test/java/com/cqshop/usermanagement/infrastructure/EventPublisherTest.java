package com.cqshop.usermanagement.infrastructure;

import com.cqshop.avro.AvroMessageBuilder;
import com.cqshop.usermanagement.domain.event.UserAccountCreated;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 13/12/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class EventPublisherTest {

    @Mock
    private EventsStreams eventsStreams;

    @Mock
    private AvroMessageBuilder messageBuilder;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private EventPublisher eventPublisher;

    @Test
    public void shouldBuildProperConversionTargetClass() {
        UserAccountCreated event = new UserAccountCreated();
        com.cqshop.usermanagement.avro.UserAccountCreated convertedEvent = new com.cqshop.usermanagement.avro.UserAccountCreated();
        MessageChannel messageChannel = mock(MessageChannel.class);
        Message message = mock(Message.class);

        when(conversionService.convert(event, com.cqshop.usermanagement.avro.UserAccountCreated.class))
                .thenReturn(convertedEvent);
        when(messageBuilder.buildMessage(convertedEvent)).thenReturn(message);
        when(eventsStreams.outboundEvents()).thenReturn(messageChannel);
        when(messageChannel.send(message)).thenReturn(true);

        eventPublisher.publish(event);

    }

}