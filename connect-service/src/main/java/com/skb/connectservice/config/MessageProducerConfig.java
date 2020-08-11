package com.skb.connectservice.config;


import com.fasterxml.jackson.databind.JsonSerializer;
import com.skb.connectservice.adapter.MessageSender;
import com.skb.connectservice.dto.WatchInfoDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessageProducerConfig {

    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootStrapServers;

    @Value(value = "{$kafka.producer.key-serializer}")
    private String keySerializer;

    @Value(value = "{kafka.producer.value-serializer}")
    private String valueSerializer;

    @Bean
    public Map<String,Object> producerConfigs(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, WatchInfoDto.Request> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, WatchInfoDto.Request> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public MessageSender messageSender(){
        return new MessageSender();
    }
}
