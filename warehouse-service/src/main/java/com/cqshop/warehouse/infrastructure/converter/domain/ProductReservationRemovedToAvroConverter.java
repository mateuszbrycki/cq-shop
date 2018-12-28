package com.cqshop.warehouse.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.warehouse.domain.event.ProductReservationCreated;
import com.cqshop.warehouse.domain.event.ProductReservationRemoved;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@TypeConverter
public class ProductReservationRemovedToAvroConverter implements Converter<ProductReservationRemoved, com.cqshop.warehouse.avro.ProductReservationRemoved> {

    @Override
    public com.cqshop.warehouse.avro.ProductReservationRemoved convert(ProductReservationRemoved source) {

        com.cqshop.warehouse.avro.ProductReservationRemoved productReservationRemoved = new com.cqshop.warehouse.avro.ProductReservationRemoved();
        productReservationRemoved.setProductId(source.getProductId());
        productReservationRemoved.setId(source.getId());
        productReservationRemoved.setUserId(source.getUserId());
        productReservationRemoved.setTimestamp(System.currentTimeMillis());

        return productReservationRemoved;
    }
}
