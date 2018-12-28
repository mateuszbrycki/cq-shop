package com.cqshop.warehouse.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.warehouse.domain.event.ProductReservationCreated;
import com.cqshop.warehouse.domain.event.ReservationMaterialized;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@TypeConverter
public class ReservationMaterializedToAvroConverter implements Converter<ReservationMaterialized, com.cqshop.warehouse.avro.ReservationMaterialized> {

    @Override
    public com.cqshop.warehouse.avro.ReservationMaterialized convert(ReservationMaterialized source) {

        com.cqshop.warehouse.avro.ReservationMaterialized reservationMaterialized = new com.cqshop.warehouse.avro.ReservationMaterialized();
        reservationMaterialized.setProductId(source.getProductId());
        reservationMaterialized.setUserId(source.getUserId());
        reservationMaterialized.setReservationId(source.getReservationId());
        reservationMaterialized.setQuantity(source.getQuantity());
        reservationMaterialized.setMaterializationDate(source.getMaterializationDate());
        reservationMaterialized.setTimestamp(System.currentTimeMillis());

        return reservationMaterialized;
    }
}
