package com.cqshop.warehouse.application.handler;

import com.cqshop.cqrs.common.handler.CommandHandler;
import com.cqshop.cqrs.common.handler.CommandHandlerAnnotation;
import com.cqshop.warehouse.application.command.MaterializeUserReservations;
import com.cqshop.warehouse.application.command.ProductAdditionRequested;
import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.service.MaterializationService;
import com.cqshop.warehouse.domain.service.ProductAddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@CommandHandlerAnnotation
public class MaterializeUserReservationsHandler implements CommandHandler<MaterializeUserReservations, Boolean> {

    private final MaterializationService materializationService;

    @Override
    public Boolean handle(MaterializeUserReservations materializeUserReservations) {

        log.info("Received materializeUserReservations: " + materializeUserReservations);

        materializationService.materializeReservationsForUser(materializeUserReservations.getUserId());

        return true;
    }

}
