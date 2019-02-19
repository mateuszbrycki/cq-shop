package com.cqshop.cqrs.common.handler.command;

import com.cqshop.cqrs.common.command.ApplicationCommand;
import com.cqshop.cqrs.common.handler.AbstractHandlerProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@Component
public class CommandHandlerProvider extends AbstractHandlerProvider {

    public CommandHandlerProvider(ConfigurableListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        registerHandlers(CommandHandler.class);
    }

    public CommandHandler findHandler(ApplicationCommand command) {

        String beanName = handlers.get(command.getClass().toString());
        if (beanName == null) {
            throw new RuntimeException("command handler not found. Command class is " + command.getClass());
        }

        CommandHandler handler = beanFactory.getBean(beanName, CommandHandler.class);

        return handler;
    }
}
