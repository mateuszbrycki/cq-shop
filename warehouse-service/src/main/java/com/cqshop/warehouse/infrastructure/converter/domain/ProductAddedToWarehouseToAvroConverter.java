package com.cqshop.warehouse.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.warehouse.domain.event.ProductAddedToWarehouse;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@TypeConverter
public class ProductAddedToWarehouseToAvroConverter implements Converter<ProductAddedToWarehouse, com.cqshop.warehouse.avro.ProductAddedToWarehouse> {

    @Override
    public com.cqshop.warehouse.avro.ProductAddedToWarehouse convert(ProductAddedToWarehouse source) {

        com.cqshop.warehouse.avro.ProductAddedToWarehouse productAddedToWarehouse = new com.cqshop.warehouse.avro.ProductAddedToWarehouse();
        productAddedToWarehouse.setCode(source.getCode());
        productAddedToWarehouse.setName(source.getName());
        productAddedToWarehouse.setProductId(source.getId());
        productAddedToWarehouse.setQuantity(source.getQuantity());
        productAddedToWarehouse.setTimestamp(System.currentTimeMillis());

        return productAddedToWarehouse;
    }
}
