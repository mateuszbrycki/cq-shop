package com.cqshop.notification.config;

import com.cqshop.avro.AvroMimeType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.cloud.stream.schema.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.stream.schema.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

import java.io.IOException;

/**
 * Created by Mateusz Brycki on 25/10/2018.
 */
@Configuration
class SchemaRegistryConfig {

    @Bean
    public SchemaRegistryClient schemaRegistryClient(@Value("${spring.cloud.stream.schemaRegistryClient.endpoint}") String endpoint) {
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(endpoint);
        return client;
    }

    @Bean
    public MessageConverter messageConverter() throws IOException {
        AvroSchemaMessageConverter converter = new AvroSchemaMessageConverter(MimeType.valueOf(AvroMimeType.AVRO_MIME_TYPE));
        return converter;
    }
}
