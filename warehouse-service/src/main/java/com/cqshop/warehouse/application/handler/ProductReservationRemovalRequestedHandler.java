package com.cqshop.warehouse.application.handler;

import com.cqshop.cqrs.common.handler.command.CommandHandler;
import com.cqshop.cqrs.common.handler.command.CommandHandlerAnnotation;
import com.cqshop.warehouse.application.command.ProductReservationRemovalRequested;
import com.cqshop.warehouse.application.command.ProductReservationRequested;
import com.cqshop.warehouse.domain.service.ProductReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class ProductReservationRemovalRequestedHandler implements CommandHandler<ProductReservationRemovalRequested, Boolean> {

    private final ProductReservationService productReservationService;

    @Override
    public Boolean handle(ProductReservationRemovalRequested productReservationRemovalRequested) {

        log.info("Received productReservationRemovalRequested: " + productReservationRemovalRequested);

        return productReservationService.remove(
                productReservationRemovalRequested.getProductId(),
                productReservationRemovalRequested.getUserId()
        );
    }

}
