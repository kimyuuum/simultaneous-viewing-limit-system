package com.skb.checkservice.controller;

import com.skb.checkservice.domain.WatchInfo.WatchInfo;
import com.skb.checkservice.domain.WatchInfo.WatchInfoDto;
import com.skb.checkservice.service.CheckViewingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectController {

    private final CheckViewingService checkViewingService;

    public ConnectController(CheckViewingService checkViewingService) {
        this.checkViewingService = checkViewingService;
    }

    @PostMapping("/connect")
    public boolean connectUser(@RequestBody WatchInfoDto.Request request) {
        return checkViewingService.checkLog(request);
    }

}
