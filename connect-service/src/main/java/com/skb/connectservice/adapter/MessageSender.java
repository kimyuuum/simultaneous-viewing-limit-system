package com.skb.connectservice.adapter;

import com.skb.connectservice.dto.WatchInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

    private static final String topic = "connectNewUser";

    @Autowired
    private KafkaTemplate<String, WatchInfoDto.Request> kafkaTemplate;

    public void sendMessage(WatchInfoDto.Request dto){
        LOGGER.info("sending watch request= '{}'",dto.toEntity());
        Message<String> message = MessageBuilder
                                                                .withPayload(dto.toEntity().toString())
                                                                .setHeader(KafkaHeaders.TOPIC,topic)
                                                                .build();
        kafkaTemplate.send(message);
    }

}
