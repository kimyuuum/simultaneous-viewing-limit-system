package com.skb.checkservice.adapter;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic,message);
        logger.info("produce user exists complete");
    }
}
