package com.skb.checkservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skb.checkservice.domain.User;
import com.skb.checkservice.dto.WatchInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisClusterService {

    @Qualifier("serializingObjectMapper")
    @Autowired
    ObjectMapper objectMapper;

    private final RedisTemplate<String,User> redisTemplate;

    public RedisClusterService(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public User create(WatchInfoDto.Request dto){
        User newUser = User.builder()
                            .pcid(dto.getPcid())
                            .episodeId(dto.getEpisodeId())
                            .stbId(dto.getStbId())
                            .playStart(dto.getPlayStart())
                            .macAddress(dto.getMacAddress())
                            .running(true)
                            .build();

        redisTemplate.opsForHash().put(dto.getStbId(), dto.getEpisodeId(),newUser);
        return objectMapper.convertValue(redisTemplate.opsForHash().get(dto.getStbId(),dto.getEpisodeId()),User.class);
    }

    public boolean checkIsRunning(String stbId, String episodeId){
        //if VOD Log presents check is running
        if(redisTemplate.opsForHash().hasKey(stbId,episodeId)){
            User user = objectMapper.convertValue(redisTemplate.opsForHash().get(stbId,episodeId),User.class);
            return user.isRunning();
        }else{
            return false;
        }
    }

    public String getExistUser(String stbId, String episodeId){
        return objectMapper.convertValue(redisTemplate.opsForHash().get(stbId,episodeId),User.class).getPcid();
    }
}
