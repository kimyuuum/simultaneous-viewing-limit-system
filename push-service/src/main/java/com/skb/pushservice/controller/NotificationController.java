package com.skb.pushservice.controller;

import com.skb.pushservice.domain.Notification;
import com.skb.pushservice.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/notifications")
    public String notifications(){
        return "notifications";
    }

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> send(){

        Notification notify = Notification.builder()
                                            .message("hello")
                                            .build();

        notificationService.notify(notify, "UserA");

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
