package com.skb.connectservice.controller;

import com.skb.connectservice.dto.WatchInfoDto;
import com.skb.connectservice.service.ConnectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watch")
public class WatchController {


    private final ConnectService connectService;

    public WatchController(ConnectService connectService) {
        this.connectService = connectService;
    }

    @PostMapping
    public void sendRequest(@RequestBody WatchInfoDto.Request dto){
        connectService.connectUser(dto);
    }

}
