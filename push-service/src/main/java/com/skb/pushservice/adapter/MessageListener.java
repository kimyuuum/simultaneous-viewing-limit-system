package com.skb.pushservice.adapter;

import com.skb.pushservice.dto.ExistDto;
import com.skb.pushservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final String existTopic = "userExists";

    private final NotificationService notificationService;

    public MessageListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = existTopic, containerFactory = "kafkaListenerContainerFactory", groupId = "exist-info-group")
    public void consumeExistUserInfo(@Payload ExistDto.Response payload){
        notificationService.notifyUserExists(payload);
    }
}
