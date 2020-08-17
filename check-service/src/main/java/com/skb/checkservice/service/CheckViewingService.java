package com.skb.checkservice.service;

import com.skb.checkservice.adapter.MessageSender;
import com.skb.checkservice.dto.UserDto;
import com.skb.checkservice.dto.WatchInfoDto;
import org.springframework.stereotype.Service;

@Service
public class CheckViewingService {

    private final RedisClusterService redisClusterService;
    private final MessageSender messageSender;

    public CheckViewingService(RedisClusterService redisClusterService, MessageSender messageSender) {
        this.redisClusterService = redisClusterService;
        this.messageSender = messageSender;
    }

    public void checkLog(WatchInfoDto.Request dto){
        boolean isRunning = redisClusterService.checkIsRunning(dto.getStbId(),dto.getEpisodeId());
        if(isRunning){
            // push alert to new User another user already exists -> user Kafka message sender
            String topic = "userExists";
            String existUser = redisClusterService.getExistUser(dto.getStbId(),dto.getEpisodeId());

            UserDto.Response response = UserDto.Response.builder()
                                                    .newUser(dto.getPcid())
                                                    .existUser(existUser)
                                                    .build();

            messageSender.sendMessage(topic,response);
        }else{
            redisClusterService.create(dto);
        }
    }
}

