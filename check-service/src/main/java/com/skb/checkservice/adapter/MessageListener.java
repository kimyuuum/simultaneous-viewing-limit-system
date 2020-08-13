package com.skb.checkservice.adapter;


import com.skb.checkservice.dto.WatchInfoDto;
import com.skb.checkservice.service.CheckViewingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final String topic = "connectNewUser";

    private final CheckViewingService checkViewingService;

    public MessageListener(CheckViewingService checkViewingService) {
        this.checkViewingService = checkViewingService;
    }

    @KafkaListener(topics = topic, containerFactory = "kafkaListenerContainerFactory",groupId = "watch-info-group")
    public void consumeWatchInfo(@Payload WatchInfoDto.Request payload){
       checkViewingService.checkLog(payload);
    }

}
