package com.cqshop.warehouse.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.warehouse.domain.event.ProductAddedToWarehouse;
import com.cqshop.warehouse.domain.event.ProductUpdatedInWarehouse;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 26/12/2018.
 */
@TypeConverter
public class ProductUpdatedInWarehouseToAvroConverter implements Converter<ProductUpdatedInWarehouse, com.cqshop.warehouse.avro.ProductUpdatedInWarehouse> {

    @Override
    public com.cqshop.warehouse.avro.ProductUpdatedInWarehouse convert(ProductUpdatedInWarehouse source) {

        com.cqshop.warehouse.avro.ProductUpdatedInWarehouse productUpdatedInWarehouse = new com.cqshop.warehouse.avro.ProductUpdatedInWarehouse();
        productUpdatedInWarehouse.setCode(source.getCode());
        productUpdatedInWarehouse.setName(source.getName());
        productUpdatedInWarehouse.setProductId(source.getId());
        productUpdatedInWarehouse.setQuantity(source.getQuantity());
        productUpdatedInWarehouse.setTimestamp(System.currentTimeMillis());

        return productUpdatedInWarehouse;
    }
}
