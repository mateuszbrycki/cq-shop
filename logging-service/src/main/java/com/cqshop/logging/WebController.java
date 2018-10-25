package com.cqshop.logging;

import com.cqshop.logging.usermanagement.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Brycki on 18/10/2018.
 */
@RestController
public class WebController {

    @Autowired
    EventPublisher publisher;

    @GetMapping("/")
    public void publish() {
        publisher.publish();
    }
}
