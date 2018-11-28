package com.cqshop.notification;

import com.cqshop.notification.domain.event.ActivationLinkSent;
import com.cqshop.notification.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Brycki on 25/11/2018.
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final EventPublisher eventPublisher;


    @RequestMapping("/")
    @GetMapping
    public void controller() {
        eventPublisher.publish(ActivationLinkSent.builder().username("test").email("test").build());
    }
}
