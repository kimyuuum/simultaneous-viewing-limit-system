package com.skb.pushservice.service;

import com.skb.pushservice.domain.Exist.ExistDto;
import com.skb.pushservice.domain.WatchInfo.WatchInfoDto;
import com.skb.pushservice.message.MessageSender;
import org.springframework.stereotype.Service;

@Service
public class ConnectService {

    private final MessageSender messageSender;
    private final NotificationService notificationService;

    private static final String newTopic = "connectNewUser";
    private static final String forceTopic = "forceConnect";
    private static final String disconnectTopic = "disconnect";

    public ConnectService(MessageSender messageSender, NotificationService notificationService) {
        this.messageSender = messageSender;
        this.notificationService = notificationService;
    }

    public void connectUser(WatchInfoDto.Request request) {
        messageSender.sendMessage(newTopic, request);
    }

    public void forceConnect(ExistDto.Request request) {

        //push alert to exist user
        notificationService.notifyDisconnect(new ExistDto.Response(request.getNewUser(), request.getExistUser()));

        //update watch info in redis
        messageSender.sendMessage(forceTopic, request.getWatchInfo());

    }

    public void stopConnect(WatchInfoDto.Request request) {

        messageSender.sendMessage(disconnectTopic, request);

    }

}