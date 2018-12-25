package com.cqshop.usermanagement.infrastructure.listener;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.kafka.listener.AbstractEventListener;
import com.cqshop.usermanagement.avro.UserAccountCreated;
import com.cqshop.usermanagement.domain.repository.UserRepository;
import com.cqshop.usermanagement.domain.service.AccountActivationService;
import com.cqshop.usermanagement.infrastructure.EventsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.core.convert.ConversionService;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class UserAccountCreatedEventListener extends AbstractEventListener {

    private final AccountActivationService accountActivationService;
    private final UserRepository userRepository;

    public UserAccountCreatedEventListener(AccountActivationService accountActivationService,
                                           UserRepository userRepository,
                                           @Qualifier("mvcConversionService") ConversionService conversionService) {
        super(conversionService);
        this.accountActivationService = accountActivationService;
        this.userRepository = userRepository;
    }

    @StreamListener(target = EventsStreams.INPUT, condition = "headers['event-type']=='UserAccountCreated'")
    public void handleEvent(@Payload UserAccountCreated event) {
            log.info("Received UserAccountCreated" + event);
            //TODO mbrycki should create a application command and handle creation in the handler
            Consumer<UserAccountCreated> handleUserAccountCreatedEvent = (convertedEvent) -> userRepository.findById(convertedEvent.getUserId())
                    .ifPresent(accountActivationService::generateActivationCodeForRegisteredUser);

            handleUserAccountCreatedEvent.accept(event);
    }

}
