package com.cqshop.cqrs.common.handler;

import com.cqshop.cqrs.common.command.ApplicationCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
public class CommandHandlerProvider implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CommandHandlerProvider.class);


    private final ConfigurableListableBeanFactory beanFactory;
    private final Map<String, String> handlers = new HashMap<String, String>();

    public CommandHandlerProvider(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public CommandHandler findHandler(ApplicationCommand command) {

        String beanName = handlers.get(command.getClass().toString());
        if (beanName == null) {
            throw new RuntimeException("command handler not found. Command class is " + command.getClass());
        }

        CommandHandler handler = beanFactory.getBean(beanName, CommandHandler.class);

        return handler;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        handlers.clear();

        String[] commandHandlerNames = beanFactory.getBeanNamesForType(CommandHandler.class);

        for (String beanName : commandHandlerNames) {
            BeanDefinition commandHandler = beanFactory.getBeanDefinition(beanName);

            try {
                Class<?> handlerClass = Class.forName(commandHandler.getBeanClassName());
                handlers.put(getHandledCommandType(handlerClass).toString(), beanName);
                logger.info("Registered " + handlerClass + " command handler");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Class<?> getHandledCommandType(Class<?> clazz) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        ParameterizedType type = findByRawType(genericInterfaces, CommandHandler.class);
        return (Class<?>) type.getActualTypeArguments()[0];
    }

    private ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parametrized = (ParameterizedType) type;
                if (expectedRawType.equals(parametrized.getRawType())) {
                    return parametrized;
                }
            }
        }
        throw new RuntimeException();
    }
}
