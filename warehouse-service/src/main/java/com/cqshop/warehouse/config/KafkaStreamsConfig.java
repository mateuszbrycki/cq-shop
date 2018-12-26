package com.cqshop.warehouse.config;

import com.cqshop.warehouse.infrastructure.EventsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */
@EnableBinding({EventsStreams.class})
public class KafkaStreamsConfig {
}
