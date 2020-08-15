package com.skb.checkservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skb.checkservice.domain.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;



@SpringBootTest
class CheckServiceApplicationTests {


    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Qualifier("serializingObjectMapper")
    @Autowired
    ObjectMapper objectMapper;

    private User newUser() {
        return User.builder()
                .macAddress("testMacNum")
                .playStart("2010-10-10'T'08:03:02")
                .playEnd("2020-10-10'T'12:01:01")
                .stbId("stbId")
                .episodeId("epId")
                .pcid("pcId")
                .running(true)
                .build();
    }

    @Test
    void testJsonDataWithHash() throws Exception {
        //test User Object put,get from redis

        String stbId = "stbId";
        String episodeId = "epId";

        redisTemplate.opsForHash().put(stbId, episodeId, newUser());

        Object result = redisTemplate.opsForHash().get(stbId,episodeId);
        User user = objectMapper.convertValue(result,User.class);

        Logger logger = LoggerFactory.getLogger(CheckServiceApplicationTests.class);
        logger.info("{}",user);
    }

}
