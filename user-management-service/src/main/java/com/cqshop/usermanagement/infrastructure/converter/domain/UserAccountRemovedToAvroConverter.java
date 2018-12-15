package com.cqshop.usermanagement.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.domain.event.UserAccountRemoved;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@TypeConverter
public class UserAccountRemovedToAvroConverter implements Converter<UserAccountRemoved, com.cqshop.usermanagement.avro.UserAccountRemoved> {

    @Override
    public com.cqshop.usermanagement.avro.UserAccountRemoved convert(UserAccountRemoved source) {

        com.cqshop.usermanagement.avro.UserAccountRemoved userCreatedEvent = new com.cqshop.usermanagement.avro.UserAccountRemoved();
        userCreatedEvent.setTimestamp(System.currentTimeMillis());
        userCreatedEvent.setUserId(source.getUserId());
        return userCreatedEvent;
    }
}
