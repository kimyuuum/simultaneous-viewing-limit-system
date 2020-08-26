package com.skb.checkservice.service;

import com.skb.checkservice.domain.User.UserDto;
import com.skb.checkservice.domain.WatchInfo.WatchInfoDto;
import com.skb.checkservice.message.MessageSender;
import org.springframework.stereotype.Service;

@Service
public class CheckViewingService {

    private final RedisClusterService redisClusterService;
    private final MessageSender messageSender;

    private static final String existTopic = "userExists";

    public CheckViewingService(RedisClusterService redisClusterService, MessageSender messageSender) {
        this.redisClusterService = redisClusterService;
        this.messageSender = messageSender;
    }

    public boolean checkLog(WatchInfoDto.Request request) {

        boolean isCreated = false;
        UserDto.Response response;

        boolean isRunning = redisClusterService.checkIsRunning(request.getStbId(), request.getEpisodeId());
        if (isRunning) {
            String existUser = redisClusterService.getExistUser(request.getStbId(), request.getEpisodeId());

            response = UserDto.Response.builder()
                                       .newUser(request.getPcid())
                                       .existUser(existUser)
                                       .build();

            messageSender.sendMessage(existTopic, response);
        } else {
            redisClusterService.create(request);
            isCreated = true;
        }

        return isCreated;

    }

    public void updateLog(WatchInfoDto.Request dto) {
        redisClusterService.update(dto);
    }

}

