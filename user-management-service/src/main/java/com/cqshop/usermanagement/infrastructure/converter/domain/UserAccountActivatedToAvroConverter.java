package com.cqshop.usermanagement.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.domain.event.UserAccountActivated;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@TypeConverter
public class UserAccountActivatedToAvroConverter implements Converter<UserAccountActivated, com.cqshop.usermanagement.avro.UserAccountActivated> {

    @Override
    public com.cqshop.usermanagement.avro.UserAccountActivated convert(UserAccountActivated source) {

        com.cqshop.usermanagement.avro.UserAccountActivated userAccountActivated = new com.cqshop.usermanagement.avro.UserAccountActivated();

        userAccountActivated.setUserId(source.getUserId());
        userAccountActivated.setTimestamp(System.currentTimeMillis());

        return userAccountActivated;
    }
}
