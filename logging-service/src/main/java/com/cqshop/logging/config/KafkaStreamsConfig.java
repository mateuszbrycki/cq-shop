package com.cqshop.logging.config;

import com.cqshop.logging.usermanagement.StreamsConfig;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */
@EnableBinding(StreamsConfig.class)
public class KafkaStreamsConfig {
}
