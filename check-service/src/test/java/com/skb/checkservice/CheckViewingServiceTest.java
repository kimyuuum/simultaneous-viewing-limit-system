package com.skb.checkservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skb.checkservice.domain.WatchInfo.WatchInfoDto;
import com.skb.checkservice.service.CheckViewingService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileReader;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckViewingServiceTest {

    @Autowired
    private CheckViewingService checkViewingService;

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

    @Test
    public void 시청이력_확인() {

        //given
        WatchInfoDto.Request dto = createDtoFromJson();

        //when
        checkViewingService.checkLog(dto);

    }

    @Test
    public void 시청이력_업데이트() {

        //given
        WatchInfoDto.Request dto = createDtoFromJson();

        //when
        checkViewingService.updateLog(dto);
    }


}
