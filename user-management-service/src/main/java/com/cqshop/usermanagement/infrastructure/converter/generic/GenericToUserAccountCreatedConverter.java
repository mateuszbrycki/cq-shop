package com.cqshop.usermanagement.infrastructure.converter.generic;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.avro.GenericUserManagementEvent;
import com.cqshop.usermanagement.domain.event.UserAccountCreated;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@TypeConverter
public class GenericToUserAccountCreatedConverter extends GenericUserManagementEventConverter<GenericUserManagementEvent, UserAccountCreated> {

    @Override
    public UserAccountCreated convert(GenericUserManagementEvent source) {
        return convertToObject(source.getPayload(), UserAccountCreated.class);
    }
}
