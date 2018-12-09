package com.cqshop.converter;

/**
 * Created by Mateusz Brycki on 08/12/2018.
 */
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

//TODO mbrycki move to common
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TypeConverter {

}