package com.cqshop.logging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Brycki on 25/10/2018.
 */
@RestController
public class WebController {

    //TODO mbrycki to properties
    private String topicName = "user-management-events";

    private KafkaTemplate<String, Object> kafkaTemplate;

    public WebController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Object msg) {
        kafkaTemplate.send(topicName, msg);
        kafkaTemplate.send(topicName + "-log", msg);
    }

    @GetMapping("/")
    public void publish() {
        sendMessage(new Entity("message from entity"));
    }
}
