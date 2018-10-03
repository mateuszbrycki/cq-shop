package com.cqshop.usermanagement.config;

import com.cqshop.usermanagement.infrastructure.EventsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Mateusz Brycki on 11/09/2018.
 */
@EnableBinding(EventsStreams.class)
public class StreamsConfig {
}
