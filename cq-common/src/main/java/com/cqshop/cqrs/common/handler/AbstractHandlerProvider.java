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
public abstract class AbstractHandlerProvider implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandlerProvider.class);

    protected final ConfigurableListableBeanFactory beanFactory;
    protected final Map<String, String> handlers = new HashMap<String, String>();

    public AbstractHandlerProvider(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected void registerHandlers(Class<?> clazz) {
        handlers.clear();

        String[] handlerNames = beanFactory.getBeanNamesForType(clazz);

        for (String beanName : handlerNames) {
            BeanDefinition handler = beanFactory.getBeanDefinition(beanName);

            try {
                Class<?> handlerClass = Class.forName(handler.getBeanClassName());
                handlers.put(getHandledCommandType(handlerClass, clazz).toString(), beanName);
                logger.info("Registered " + handlerClass + " command handler");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Class<?> getHandledCommandType(Class<?> clazz, Class<?> eventTypeHandler) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        ParameterizedType type = findByRawType(genericInterfaces, eventTypeHandler);
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
