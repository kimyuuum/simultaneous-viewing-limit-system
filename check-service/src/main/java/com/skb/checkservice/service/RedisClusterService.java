package com.skb.checkservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skb.checkservice.domain.WatchInfo.WatchInfo;
import com.skb.checkservice.domain.WatchInfo.WatchInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisClusterService {

    @Autowired
    ObjectMapper objectMapper;

    private final RedisTemplate<String, WatchInfo> redisTemplate;

    public RedisClusterService(RedisTemplate<String, WatchInfo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void create(WatchInfoDto.Request dto) {
        redisTemplate.opsForHash()
                     .put(dto.getStbId(), dto.getEpisodeId(), dto.toEntity());
    }

    public void update(WatchInfoDto.Request dto) {
        //Update redis Data
        redisTemplate.opsForHash()
                     .delete(dto.getStbId(), dto.getEpisodeId());
        create(dto);
    }

    public boolean checkIsRunning(String stbId, String episodeId) {
        //if VOD Log presents check is running
        if (redisTemplate.opsForHash()
                         .hasKey(stbId, episodeId)) {
            WatchInfo user = objectMapper.convertValue(redisTemplate.opsForHash()
                                                                    .get(stbId, episodeId), WatchInfo.class);
            return user.isRunning();
        } else {
            return false;
        }
    }

    public String getExistUser(String stbId, String episodeId) {
        WatchInfo existUser = objectMapper.convertValue(redisTemplate.opsForHash()
                                                                     .get(stbId, episodeId), WatchInfo.class);
        return existUser.getPcid();
    }

}
