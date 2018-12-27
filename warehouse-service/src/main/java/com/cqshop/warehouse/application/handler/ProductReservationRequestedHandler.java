package com.cqshop.warehouse.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.warehouse.application.command.ProductAdditionRequested;
import com.cqshop.warehouse.application.command.ProductReservationRequested;
import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.service.ProductAddingService;
import com.cqshop.warehouse.domain.service.ProductReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class ProductReservationRequestedHandler implements CommandHandler<ProductReservationRequested, Boolean> {

    private final ProductReservationService productReservationService;

    @Override
    public Boolean handle(ProductReservationRequested productReservationRequested) {

        log.info("Received productReservationRequested: " + productReservationRequested);

        return productReservationService.reserve(
                productReservationRequested.getProductId(),
                productReservationRequested.getQuantity(),
                productReservationRequested.getUserId()
        );
    }

}
