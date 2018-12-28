package com.cqshop.notification.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.notification.domain.event.ActivationLinkSent;
import com.cqshop.notification.domain.event.OrderConfirmationSent;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@TypeConverter
public class ActivationLinkSentToAvroConverter implements Converter<ActivationLinkSent, com.cqshop.notification.avro.ActivationLinkSent> {

    @Override
    public com.cqshop.notification.avro.ActivationLinkSent convert(ActivationLinkSent source) {

        com.cqshop.notification.avro.ActivationLinkSent orderConfirmationSent = new com.cqshop.notification.avro.ActivationLinkSent();
        orderConfirmationSent.setEmail(source.getEmail());
        orderConfirmationSent.setUsername(source.getUsername());
        orderConfirmationSent.setTimestamp(System.currentTimeMillis());

        return orderConfirmationSent;
    }
}
