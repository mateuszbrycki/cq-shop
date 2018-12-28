package com.cqshop.logging.warehouse;

import com.cqshop.warehouse.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@Slf4j
@Component
public class WarehouseEventsListener {

    @StreamListener(target = WarehouseStreamsConfig.WAREHOUSE_EVENTS, condition = "headers['event-type']=='ProductAddedToWarehouse'")
    public void handleProductAddedToWarehouse(ProductAddedToWarehouse event) {
        log.info("Received ProductAddedToWarehouse event " + event.toString());
    }

    @StreamListener(target = WarehouseStreamsConfig.WAREHOUSE_EVENTS, condition = "headers['event-type']=='ProductUpdatedInWarehouse'")
    public void handleProductUpdatedInWarehouse(ProductUpdatedInWarehouse event) {
        log.info("Received ProductUpdatedInWarehouse event " + event.toString());
    }

    @StreamListener(target = WarehouseStreamsConfig.WAREHOUSE_EVENTS, condition = "headers['event-type']=='ProductReservationCreated'")
    public void handleProductReservationCreated(ProductReservationCreated event) {
        log.info("Received ProductReservationCreated event " + event.toString());
    }

    @StreamListener(target = WarehouseStreamsConfig.WAREHOUSE_EVENTS, condition = "headers['event-type']=='ProductReservationRemoved'")
    public void handleProductReservationRemoved(ProductReservationRemoved event) {
        log.info("Received ProductReservationRemoved event " + event.toString());
    }

    @StreamListener(target = WarehouseStreamsConfig.WAREHOUSE_EVENTS, condition = "headers['event-type']=='ReservationMaterialized'")
    public void handleReservationMaterialized(ReservationMaterialized event) {
        log.info("Received ReservationMaterialized event " + event.toString());
    }
}
