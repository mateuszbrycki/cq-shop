package com.cqshop.usermanagement.infrastructure.converter.domain;

import com.cqshop.converter.TypeConverter;
import com.cqshop.usermanagement.domain.event.AccountActivationCodeCreated;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
@TypeConverter
public class AccountActivationCodeCreatedToAvroConverter implements Converter<AccountActivationCodeCreated, com.cqshop.usermanagement.avro.AccountActivationCodeCreated> {

    @Override
    public com.cqshop.usermanagement.avro.AccountActivationCodeCreated convert(AccountActivationCodeCreated source) {

        com.cqshop.usermanagement.avro.AccountActivationCodeCreated accountActivationCodeCreated = new com.cqshop.usermanagement.avro.AccountActivationCodeCreated();

        accountActivationCodeCreated.setUserId(source.getUserId());
        accountActivationCodeCreated.setActivationCode(source.getActivationCode());
        accountActivationCodeCreated.setTimestamp(System.currentTimeMillis());

        return accountActivationCodeCreated;
    }
}
