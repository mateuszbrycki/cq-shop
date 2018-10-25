package com.cqshop.logging;

import com.cqshop.avro.AvroMimeType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.cloud.stream.schema.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.stream.schema.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

import java.io.IOException;

/**
 * Created by Mateusz Brycki on 13/10/2018.
 */
@Configuration
public class AppConfig
{
    @Bean
    public MessageConverter messageConverter() throws IOException {
        AvroSchemaMessageConverter converter = new AvroSchemaMessageConverter(MimeType.valueOf(AvroMimeType.AVRO_MIME_TYPE));
        /*ClassPathResource schemaLocation = new ClassPathResource("schemas/userManagementEvents.avsc");
        converter.setSchemaLocation(schemaLocation);*/
        return converter;
    }


    @Bean
    public SchemaRegistryClient schemaRegistryClient(@Value("${logging-service.schemaRegistryClient.endpoint}") String endpoint){
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(endpoint);
        return client;
    }
}
