package com.skb.checkservice.controller;

import com.skb.checkservice.dto.UserDto;
import com.skb.checkservice.service.RedisClusterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class UserController {

    private final RedisClusterService redisClusterService;

    public UserController(RedisClusterService redisClusterService) {
        this.redisClusterService = redisClusterService;
    }

    @PostMapping
    public UserDto.Response createUserLog(@RequestBody UserDto.Request request){
        return new UserDto.Response(redisClusterService.create(request));
    }
}