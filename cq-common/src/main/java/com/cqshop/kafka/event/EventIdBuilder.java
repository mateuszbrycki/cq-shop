package com.cqshop.kafka.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 09/12/2018.
 */
@Component
public class EventIdBuilder {

    @Value("${spring.application.name}")
    private String applicationName;


    public String getEventId(String eventName) {

        return applicationName + "-" + eventName;

    }

    public String getEventId(String eventName, String applicationName) {

        return applicationName + "-" + eventName;

    }

}
