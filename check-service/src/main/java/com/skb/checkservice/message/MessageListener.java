package com.skb.checkservice.message;


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

    @KafkaListener(topics = "forceConnect", containerFactory = "forceKafkaListenerContainerFactory", groupId = "force-info-group")
    public void consumeForceConnectInfo(@Payload WatchInfoDto.Request payload) {
        checkViewingService.updateLog(payload);
    }

    @KafkaListener(topics = "disconnect", containerFactory = "disconnectKafkaListenerContainerFactory", groupId = "disconnect-info-group")
    public void consumeDisconnect(@Payload WatchInfoDto.Request payload) {
        checkViewingService.updateLog(payload);
    }
}
