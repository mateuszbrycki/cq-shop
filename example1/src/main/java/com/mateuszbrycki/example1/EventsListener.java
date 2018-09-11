package com.mateuszbrycki.example1;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 11/09/2018.
 */
@Component
public class EventsListener {

    @StreamListener(EventsStreams.INPUT)
    public void handleEvent(@Payload Event event) {
        System.out.println("EVENT:" + event.toString());
    }
}
