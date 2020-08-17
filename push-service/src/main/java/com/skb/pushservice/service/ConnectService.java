package com.skb.pushservice.service;

import com.skb.pushservice.adapter.MessageSender;
import com.skb.pushservice.dto.ConnectDto;
import com.skb.pushservice.dto.WatchInfoDto;
import org.springframework.stereotype.Service;

@Service
public class ConnectService {

    private final MessageSender messageSender;

    public ConnectService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void connectUser(ConnectDto.Request request){
        String topic = "connectNewUser";

        //Declare detail watch information for example
        WatchInfoDto.Request dto = WatchInfoDto.Request.builder()
                                                        .pcid(request.getPcid())
                                                        .episodeId("episode_id")
                                                        .stbId("stb_id")
                                                        .playStart("2020-08-17'T'01:15:00")
                                                        .macAddress("mac_address")
                                                        .build();

        messageSender.sendMessage(topic,dto);
    }

}