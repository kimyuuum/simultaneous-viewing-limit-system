package com.skb.pushservice.service;

import com.skb.pushservice.message.MessageSender;
import com.skb.pushservice.domain.Exist.ExistDto;
import com.skb.pushservice.domain.WatchInfo.WatchInfoDto;
import org.springframework.stereotype.Service;

@Service
public class ConnectService {

    private final MessageSender messageSender;
    private final NotificationService notificationService;

    public ConnectService(MessageSender messageSender, NotificationService notificationService) {
        this.messageSender = messageSender;
        this.notificationService = notificationService;
    }

    public void connectUser(WatchInfoDto.Request request){
        String topic = "connectNewUser";

        /*
         * Declare detail watch information for example
         * If you send detail watch info from client, you don't need to declare again.

        WatchInfoDto.Request dto = WatchInfoDto.Request.builder()
                                                        .pcid("pcId")
                                                        .episodeId("episode_id")
                                                        .stbId("stb_id")
                                                        .playStart("2020-08-17'T'01:15:00")
                                                        .macAddress("mac_address")
                                                        .build();
        */

        messageSender.sendMessage(topic,request);
    }

    public void forceConnect(ExistDto.Request request){

        //push alert to exist user
        notificationService.notifyDisconnect(new ExistDto.Response(request.getNewUser(),
                                                                    request.getExistUser()));

        //update watch info in redis
        String topic = "forceConnect";
        messageSender.sendMessage(topic,request.getWatchInfo());

    }
}