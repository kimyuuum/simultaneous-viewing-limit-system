package com.skb.pushservice.service;


import com.skb.pushservice.domain.Notification;
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
     *
     * @Param notification = message
     * @Param username = client
     */

    public void notify(Notification notification, String userName){
        simpMessagingTemplate.convertAndSendToUser(userName,"/queue/notify",notification);
    }

}
