package com.skb.pushservice.service;


import com.skb.pushservice.domain.Exist.ExistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Send notify to user who subscribed channel "/user/queue/notify".
     * The message will be sent to given client(user)
     * @Param newUser = new client
     * @Param existUser = exist client
     */

    public void notifyUserExists(ExistDto.Response dto){
        simpMessagingTemplate.convertAndSendToUser(dto.getNewUser(),"/queue/notify",dto);
    }

    /*
     * Send notify to user who subscribed channel "/user/queue/disconnect".
     * The message will be sent to given client(user)
     *
     */

    public void notifyDisconnect(ExistDto.Response dto){
        simpMessagingTemplate.convertAndSendToUser(dto.getExistUser(),"/queue/disconnect",dto);
    }

}
