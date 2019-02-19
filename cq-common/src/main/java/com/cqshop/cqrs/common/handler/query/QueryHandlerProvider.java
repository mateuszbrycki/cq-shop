package com.cqshop.cqrs.common.handler.query;

import com.cqshop.cqrs.common.handler.AbstractHandlerProvider;
import com.cqshop.cqrs.common.query.ApplicationQuery;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@Component
public class QueryHandlerProvider extends AbstractHandlerProvider {

    public QueryHandlerProvider(ConfigurableListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        registerHandlers(QueryHandler.class);
    }

    public QueryHandler findHandler(ApplicationQuery query) {

        String beanName = handlers.get(query.getClass().toString());
        if (beanName == null) {
            throw new RuntimeException("Query handler not found. Query class is " + query.getClass());
        }

        QueryHandler handler = beanFactory.getBean(beanName, QueryHandler.class);

        return handler;
    }
}
