package com.skb.checkservice.message;

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

    private final KafkaTemplate<String, UserDto.Response> dtoKafkaTemplate;
    private final KafkaTemplate<String,String> stringKafkaTemplate;

    public void sendMessage(String topic, UserDto.Response dto) {
        dtoKafkaTemplate.send(topic, dto);
        logger.info("produce user exists complete");
    }

    public void sendMessage(String topic, String message){
        stringKafkaTemplate.send(topic,message);
        logger.info("Produce success response : update user");
    }
}
