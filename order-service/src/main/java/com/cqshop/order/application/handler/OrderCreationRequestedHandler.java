package com.cqshop.order.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.order.application.command.OrderCreationRequested;
import com.cqshop.order.domain.service.OrderCreationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class OrderCreationRequestedHandler implements CommandHandler<OrderCreationRequested, Boolean> {

    private final OrderCreationService orderCreationService;

    @Override
    public Boolean handle(OrderCreationRequested orderCreationRequested) {

        log.info("Received orderCreationRequested: " + orderCreationRequested);

        return orderCreationService.createOrder(orderCreationRequested.getUserId()); }
}
