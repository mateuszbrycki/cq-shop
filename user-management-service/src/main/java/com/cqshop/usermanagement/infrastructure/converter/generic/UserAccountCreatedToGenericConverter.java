package com.cqshop.usermanagement.infrastructure.converter.generic;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.avro.GenericUserManagementEvent;
import com.cqshop.usermanagement.domain.event.UserAccountCreated;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@TypeConverter
public class UserAccountCreatedToGenericConverter extends GenericUserManagementEventConverter<UserAccountCreated, GenericUserManagementEvent> {

    @Override
    public GenericUserManagementEvent convert(UserAccountCreated source) {
        return convertToGeneric(source);
    }
}
