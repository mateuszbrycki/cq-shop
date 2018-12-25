package com.cqshop.cart.infrastructure.converter.domain;

import com.cqshop.cart.domain.event.UserCartCreated;
import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.avro.AccountActivationCodeCreated;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@TypeConverter
public class UserCartCreatedToAvroConverter implements Converter<UserCartCreated, com.cqshop.cart.avro.UserCartCreated> {

    @Override
    public com.cqshop.cart.avro.UserCartCreated convert(UserCartCreated source) {

        com.cqshop.cart.avro.UserCartCreated  userCartCreated = new com.cqshop.cart.avro.UserCartCreated ();

        userCartCreated.setUserId(source.getUserId());
        userCartCreated.setCartId(source.getCartId());
        userCartCreated.setTimestamp(System.currentTimeMillis());

        return userCartCreated;
    }
}

