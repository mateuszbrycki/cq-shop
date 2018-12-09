package com.cqshop.usermanagement.infrastructure.converter.generic;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.avro.GenericUserManagementEvent;
import com.cqshop.usermanagement.domain.event.AccountActivationCodeCreated;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@Slf4j
@TypeConverter
public class AccountActivationCodeCreatedToGenericConverter extends GenericUserManagementEventConverter<AccountActivationCodeCreated, GenericUserManagementEvent> {

    @Override
    public GenericUserManagementEvent convert(AccountActivationCodeCreated source) {
        return convertToGeneric(source);
    }

}
