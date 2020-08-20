package com.skb.checkservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skb.checkservice.domain.WatchInfo.WatchInfo;
import com.skb.checkservice.domain.WatchInfo.WatchInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisClusterService {

    @Qualifier("serializingObjectMapper")
    @Autowired
    ObjectMapper objectMapper;

    private final RedisTemplate<String, WatchInfo> redisTemplate;

    public RedisClusterService(RedisTemplate<String, WatchInfo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void create(WatchInfoDto.Request dto){
        redisTemplate.opsForHash().put(dto.getStbId(), dto.getEpisodeId(),dto.toEntity());
    }

    public boolean checkIsRunning(String stbId, String episodeId){
        //if VOD Log presents check is running
        if(redisTemplate.opsForHash().hasKey(stbId,episodeId)){
            WatchInfo user = objectMapper.convertValue(redisTemplate.opsForHash().get(stbId,episodeId),WatchInfo.class);
            return user.isRunning();
        }else{
            return false;
        }
    }

    public String getExistUser(String stbId, String episodeId){
        return objectMapper.convertValue(redisTemplate.opsForHash().get(stbId,episodeId),WatchInfo.class).getPcid();
    }
}
