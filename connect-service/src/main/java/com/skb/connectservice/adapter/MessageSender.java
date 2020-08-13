package com.skb.connectservice.adapter;

import com.skb.connectservice.domain.WatchInfo;
import com.skb.connectservice.dto.WatchInfoDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private final KafkaTemplate<String, WatchInfoDto.Request> kafkaTemplate;

    public void sendMessage(String topic, WatchInfoDto.Request dto){
        kafkaTemplate.send(topic,dto);
        logger.info("produce new user complete");
    }

}
