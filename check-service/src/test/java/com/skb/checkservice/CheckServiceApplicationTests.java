package com.skb.checkservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skb.checkservice.domain.WatchInfo.WatchInfo;
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
    private RedisTemplate<String, WatchInfo> redisTemplate;

    @Qualifier("serializingObjectMapper")
    @Autowired
    ObjectMapper objectMapper;

    private WatchInfo newUser() {
        return WatchInfo.builder()
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
        WatchInfo user = objectMapper.convertValue(result,WatchInfo.class);

        Logger logger = LoggerFactory.getLogger(CheckServiceApplicationTests.class);
        logger.info("{}",user);
    }

}
