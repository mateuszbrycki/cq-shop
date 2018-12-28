package com.cqshop.notification.infrastructure;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */

import com.cqshop.notification.avro.ActivationLinkSent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventsListener {


    @StreamListener(EventsStreams.INPUT)
    public void notificationServiceEventsIn(Flux<ActivationLinkSent> input) {
        input.subscribe(event -> {
                log.info("Received event ActivationLinkSent: " + event.toString());
        });
    }
}
