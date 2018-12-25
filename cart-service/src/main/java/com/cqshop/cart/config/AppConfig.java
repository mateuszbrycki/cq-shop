package com.cqshop.cart.config;

import com.cqshop.avro.AvroMimeType;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

import java.io.IOException;

/**Ń
 * Created by Mateusz Brycki on 13/10/2018.
 */
@Configuration
public class AppConfig
{
    @Bean
    public MessageConverter messageConverter() throws IOException {
        AvroSchemaMessageConverter converter = new AvroSchemaMessageConverter(MimeType.valueOf(AvroMimeType.AVRO_MIME_TYPE));
        return converter;
    }
}
