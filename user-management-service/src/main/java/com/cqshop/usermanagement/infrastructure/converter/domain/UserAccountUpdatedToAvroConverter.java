package com.cqshop.usermanagement.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.domain.event.UserAccountUpdated;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@TypeConverter
public class UserAccountUpdatedToAvroConverter implements Converter<UserAccountUpdated, com.cqshop.usermanagement.avro.UserAccountUpdated> {

    @Override
    public com.cqshop.usermanagement.avro.UserAccountUpdated convert(UserAccountUpdated source) {

        com.cqshop.usermanagement.avro.UserAccountUpdated userCreatedEvent = new com.cqshop.usermanagement.avro.UserAccountUpdated();
        userCreatedEvent.setTimestamp(System.currentTimeMillis());
        userCreatedEvent.setUserId(source.getUserId());
        userCreatedEvent.setEmail(source.getEmail());
        userCreatedEvent.setUsername(source.getUsername());

        return userCreatedEvent;
    }
}
