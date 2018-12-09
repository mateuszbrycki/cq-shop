package com.cqshop.usermanagement.infrastructure.converter.generic;

import com.cqshop.kafka.event.EventIdBuilder;
import com.cqshop.usermanagement.avro.GenericUserManagementEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;

/**
 * Created by Mateusz Brycki on 09/12/2018.
 */
@Slf4j
public abstract class GenericUserManagementEventConverter<S,T> implements Converter<S,T> {

    @Autowired
    @Qualifier("mvcConversionService")
    protected ConversionService conversionService;

    @Autowired
    private EventIdBuilder eventIdBuilder;

    protected GenericUserManagementEvent convertToGeneric(Object event) {

        GenericUserManagementEvent genericUserManagementEvent = new GenericUserManagementEvent();

        try {
            genericUserManagementEvent.setPayload(convertToJSON(event));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        genericUserManagementEvent.setTimestamp(System.currentTimeMillis());
        genericUserManagementEvent.setType(eventIdBuilder.getEventId(event.getClass().getSimpleName()));

        return genericUserManagementEvent;
    }

    protected GenericUserManagementEvent convertToGenericWithAvro(Object event, Class<?> avroType) {
        Object avroSource = conversionService.convert(event, avroType);
        return convertToGeneric(avroSource);
    }


    private String convertToJSON(Object value) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }

    protected <Z> Z convertToObject(String payload, Class<Z> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(payload, type);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
