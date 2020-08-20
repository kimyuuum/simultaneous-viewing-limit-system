package com.skb.checkservice.adapter;


import com.skb.checkservice.domain.WatchInfo.WatchInfoDto;
import com.skb.checkservice.service.CheckViewingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final CheckViewingService checkViewingService;

    public MessageListener(CheckViewingService checkViewingService) {
        this.checkViewingService = checkViewingService;
    }

    @KafkaListener(topics = "connectNewUser", containerFactory = "newKafkaListenerContainerFactory", groupId = "watch-info-group")
    public void consumeWatchInfo(@Payload WatchInfoDto.Request payload){
        checkViewingService.checkLog(payload);

    }

    @KafkaListener(topics = "forceConnect", containerFactory = "forceKafkaListenerContainerFactory", groupId = "force-info-group")
    public void consumeForceConnectInfo(@Payload WatchInfoDto.Request payload){
        checkViewingService.updateLog(payload);
    }
}
