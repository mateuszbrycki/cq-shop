package com.cqshop.warehouse.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.warehouse.domain.event.ProductAddedToWarehouse;
import com.cqshop.warehouse.domain.event.ProductReservationCreated;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@TypeConverter
public class ProductReservationCreatedToAvroConverter implements Converter<ProductReservationCreated, com.cqshop.warehouse.avro.ProductReservationCreated> {

    @Override
    public com.cqshop.warehouse.avro.ProductReservationCreated convert(ProductReservationCreated source) {

        com.cqshop.warehouse.avro.ProductReservationCreated productAddedToWarehouse = new com.cqshop.warehouse.avro.ProductReservationCreated();
        productAddedToWarehouse.setProductId(source.getProductId());
        productAddedToWarehouse.setId(source.getId());
        productAddedToWarehouse.setUserId(source.getUserId());
        productAddedToWarehouse.setQuantity(source.getQuantity());
        productAddedToWarehouse.setTimestamp(System.currentTimeMillis());

        return productAddedToWarehouse;
    }
}
