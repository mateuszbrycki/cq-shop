package com.cqshop.cqrs.common.handler.query;

import com.cqshop.cqrs.common.query.ApplicationQuery;

/**
 * Created by Mateusz Brycki on 03/10/2018.
 */
public interface QueryHandler<T extends ApplicationQuery, W> {

    W handle(T command);

}
