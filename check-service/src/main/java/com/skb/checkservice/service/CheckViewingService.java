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

    public void checkLog(WatchInfoDto.Request dto) {
        boolean isRunning = redisClusterService.checkIsRunning(dto.getStbId(), dto.getEpisodeId());
        if (isRunning) {
            // push alert to new User another user already exists -> user Kafka message sender
            String existUser = redisClusterService.getExistUser(dto.getStbId(), dto.getEpisodeId());

            UserDto.Response response = UserDto.Response.builder()
                                                        .newUser(dto.getPcid())
                                                        .existUser(existUser)
                                                        .build();

            messageSender.sendMessage(existTopic, response);
        } else {
            redisClusterService.create(dto);
        }
    }

    public void updateLog(WatchInfoDto.Request dto) {
        redisClusterService.update(dto);
    }

}

