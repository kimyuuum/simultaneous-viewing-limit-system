package com.skb.checkservice.adapter;

import com.skb.checkservice.domain.User.UserDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private final KafkaTemplate<String,UserDto.Response> kafkaTemplate;

    public void sendMessage(String topic, UserDto.Response dto){
        kafkaTemplate.send(topic,dto);
        logger.info("produce user exists complete");
    }
}
