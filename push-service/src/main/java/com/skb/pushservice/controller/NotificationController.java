package com.skb.pushservice.controller;

import com.skb.pushservice.domain.Notification;
import com.skb.pushservice.dto.ConnectDto;
import com.skb.pushservice.service.ConnectService;
import com.skb.pushservice.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController {

    private final NotificationService notificationService;

    private final ConnectService connectService;

    public NotificationController(NotificationService notificationService, ConnectService connectService) {
        this.notificationService = notificationService;
        this.connectService = connectService;
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
    public ResponseEntity<?> send(@RequestBody ConnectDto.Request request){

        //connect user to watch VOD
        connectService.connectUser(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
