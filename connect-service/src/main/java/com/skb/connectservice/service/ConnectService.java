package com.skb.connectservice.service;

import com.skb.connectservice.adapter.MessageSender;
import com.skb.connectservice.dto.WatchInfoDto;
import org.springframework.stereotype.Service;

@Service
public class ConnectService {

    private final MessageSender messageSender;

    public ConnectService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void connectUser(WatchInfoDto.Request request){
        //user 유효성 검증이 필요한가
        //user Info가 null이면 notFound긴 한데
        messageSender.sendMessage(request);
    }
}
