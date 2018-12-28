package com.cqshop.order.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@Configuration
public class FeingConfiguration {

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

}
