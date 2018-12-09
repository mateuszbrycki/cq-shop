package com.cqshop.usermanagement.infrastructure.converter.generic;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.avro.GenericUserManagementEvent;
import com.cqshop.usermanagement.domain.event.UserAccountActivated;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@TypeConverter
public class UserAccountActivatedToGenericConverter extends GenericUserManagementEventConverter<UserAccountActivated, GenericUserManagementEvent> {

    @Override
    public GenericUserManagementEvent convert(UserAccountActivated source) {
        return convertToGeneric(source);
    }
}
