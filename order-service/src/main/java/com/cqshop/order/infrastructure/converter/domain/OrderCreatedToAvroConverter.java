package com.cqshop.order.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.order.domain.event.OrderCreated;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@TypeConverter
public class OrderCreatedToAvroConverter implements Converter<OrderCreated, com.cqshop.order.avro.OrderCreated> {

    @Override
    public com.cqshop.order.avro.OrderCreated convert(OrderCreated source) {

        com.cqshop.order.avro.OrderCreated cartLineUpdated = new com.cqshop.order.avro.OrderCreated();
        cartLineUpdated.setOrderId(source.getOrderId());
        cartLineUpdated.setOrderOwner(source.getOrderOwner());
        cartLineUpdated.setCreationTime(source.getCreationTime());
        cartLineUpdated.setTimestamp(System.currentTimeMillis());

        return cartLineUpdated;
    }
}
