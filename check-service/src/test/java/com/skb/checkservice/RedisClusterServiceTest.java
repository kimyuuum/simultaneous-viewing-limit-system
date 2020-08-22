package com.skb.checkservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skb.checkservice.domain.WatchInfo.WatchInfoDto;
import com.skb.checkservice.service.RedisClusterService;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileReader;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisClusterServiceTest {

    @Autowired
    private RedisClusterService redisClusterService;

    public WatchInfoDto.Request createDtoFromJson() {

        File file = new File("/Users/kim-yumin/project/check-service/watch-info.json");
        JSONParser jsonParser = new JSONParser();
        WatchInfoDto.Request result = null;
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(jsonObject.toString(), WatchInfoDto.Request.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private WatchInfoDto.Request createDto(String stb_id, String episode_id, boolean running) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stb_id", stb_id);
        jsonObject.put("episode_id", episode_id);
        jsonObject.put("mac_address", "mac_address2");
        jsonObject.put("play_start", "2020-08-22'T'21:32:00");
        jsonObject.put("pcid", "UserA");
        jsonObject.put("running", running);

        WatchInfoDto.Request dto = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            dto = objectMapper.readValue(jsonObject.toString(), WatchInfoDto.Request.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

    @Before
    public void WatchInfo_생성() {
        //given
        WatchInfoDto.Request dto = createDtoFromJson();

        //when
        redisClusterService.create(dto);
    }

    @Test
    public void WatchInfo_수정() {
        //given
        WatchInfoDto.Request dto = createDtoFromJson();

        //when
        redisClusterService.update(dto);
    }

    @Test
    public void 시청여부확인_true() {
        //given
        String stbId = "stb_id2";
        String episodeId = "episode_id2";
        WatchInfoDto.Request dto = createDto(stbId, episodeId, true);

        //when
        Assertions.assertThat(dto)
                  .isNotNull();
        redisClusterService.create(dto);

        boolean result = redisClusterService.checkIsRunning(dto.getStbId(), dto.getEpisodeId());

        //then
        Assertions.assertThat(result)
                  .isEqualTo(true);
    }

    @Test
    public void 시청여부확인_false() {
        //given
        String stbId = "stb_id3";
        String episodeId = "episode_id3";
        WatchInfoDto.Request dto = createDto(stbId, episodeId, false);

        //when
        Assertions.assertThat(dto)
                  .isNotNull();
        redisClusterService.create(dto);

        boolean result = redisClusterService.checkIsRunning(dto.getStbId(), dto.getEpisodeId());

        //then
        Assertions.assertThat(result)
                  .isEqualTo(false);
    }

    @Test
    public void 기존시청자_확인() {
        //given
        String stbId = "stb_id4";
        String episodeId = "episode_id4";
        WatchInfoDto.Request dto = createDto(stbId, episodeId, true);
        String existUser = dto.getPcid();

        //when
        Assertions.assertThat(dto)
                  .isNotNull();
        redisClusterService.create(dto);
        String result = redisClusterService.getExistUser(stbId, episodeId);

        //then
        Assertions.assertThat(result)
                  .isEqualTo(existUser);
    }

}
