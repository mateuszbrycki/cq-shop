package com.cqshop.usermanagement.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.domain.event.UserAccountCreated;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@TypeConverter
public class UserAccountCreatedToAvroConverter implements Converter<UserAccountCreated, com.cqshop.usermanagement.avro.UserAccountCreated> {

    @Override
    public com.cqshop.usermanagement.avro.UserAccountCreated convert(UserAccountCreated source) {

        com.cqshop.usermanagement.avro.UserAccountCreated userCreatedEvent = new com.cqshop.usermanagement.avro.UserAccountCreated();
        userCreatedEvent.setTimestamp(System.currentTimeMillis());
        userCreatedEvent.setUserId(source.getUserId());
        userCreatedEvent.setEmail(source.getEmail());
        userCreatedEvent.setUsername(source.getUsername());

        return userCreatedEvent;
    }
}
