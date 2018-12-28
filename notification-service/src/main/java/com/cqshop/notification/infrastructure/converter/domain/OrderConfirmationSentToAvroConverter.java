package com.cqshop.notification.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.notification.domain.event.OrderConfirmationSent;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@TypeConverter
public class OrderConfirmationSentToAvroConverter implements Converter<OrderConfirmationSent, com.cqshop.notification.avro.OrderConfirmationSent> {

    @Override
    public com.cqshop.notification.avro.OrderConfirmationSent convert(OrderConfirmationSent source) {

        com.cqshop.notification.avro.OrderConfirmationSent orderConfirmationSent = new com.cqshop.notification.avro.OrderConfirmationSent();
        orderConfirmationSent.setUserId(source.getUserId());
        orderConfirmationSent.setOrderId(source.getOrderId());
        orderConfirmationSent.setTimestamp(System.currentTimeMillis());

        return orderConfirmationSent;
    }
}
