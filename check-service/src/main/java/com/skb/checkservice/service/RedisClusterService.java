package com.skb.checkservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skb.checkservice.domain.User;
import com.skb.checkservice.dto.UserDto;
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

    public User create(UserDto.Request dto){
        String stbId = dto.getStbId();
        String episodeId = dto.getEpisodeId();

        redisTemplate.opsForHash().put(stbId,episodeId,dto.toEntity());
        return objectMapper.convertValue(redisTemplate.opsForHash().get(stbId,episodeId),User.class);
    }


}
