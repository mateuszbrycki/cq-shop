package com.cqshop.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

/**
 * Created by Mateusz Brycki on 09/12/2018.
 */
@Slf4j
public class AbstractEventListener {
    protected final ConversionService conversionService;

    public AbstractEventListener(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    protected <T> Optional<T> convert(Object event, Class<T> targetType) {

        if (conversionService.canConvert(event.getClass(), targetType)) {
            T convert = conversionService.convert(event, targetType);
            if (convert != null) {
                return Optional.of(convert);
            }
        }

        log.error("Cannot convert payload to an Avro object" + event);
        return Optional.empty();
    }
}
