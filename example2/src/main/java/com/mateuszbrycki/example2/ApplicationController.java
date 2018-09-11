package com.mateuszbrycki.example2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 11/09/2018.
 */
@RestController
public class ApplicationController {

    private final EventsService eventsService;

    public ApplicationController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping("/send-event")
    public void sendEvent() {
        eventsService.sendEvent(
                Event.builder().
                        timestamp(123456L).
                        message(LocalDateTime.now().toString()).
                        build()
        );
    }
}
