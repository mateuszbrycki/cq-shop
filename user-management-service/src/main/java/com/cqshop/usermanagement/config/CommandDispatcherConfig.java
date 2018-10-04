package com.cqshop.usermanagement.config;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.cqrs.common.gate.SpringGate;
import com.cqshop.cqrs.common.handler.CommandHandlerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
@Configuration
public class CommandDispatcherConfig {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Bean
    public CommandHandlerProvider commandHandlerProvider() {
        return new CommandHandlerProvider(beanFactory);
    }

    @Bean
    public Gate commandDispatcher() {
        return new SpringGate(commandHandlerProvider());
    }
}
