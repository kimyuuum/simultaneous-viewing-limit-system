package com.skb.pushservice.controller;

import com.skb.pushservice.domain.Exist.ExistDto;
import com.skb.pushservice.domain.WatchInfo.WatchInfoDto;
import com.skb.pushservice.service.ConnectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController {

    private final ConnectService connectService;

    public NotificationController(ConnectService connectService) {
        this.connectService = connectService;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    //landing page
    @RequestMapping("/main")
    public String main(){return "main";}

    @RequestMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> send(@RequestBody WatchInfoDto.Request request) {

        //connect user to watch VOD
        connectService.connectUser(request);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/force")
    public ResponseEntity<?> forceConnect(@RequestBody ExistDto.Request request){

        connectService.forceConnect(request);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
