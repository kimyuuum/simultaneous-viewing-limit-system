package com.skb.checkservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skb.checkservice.domain.User;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

public class UserDto {


    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request{

        @JsonProperty("log_idx")
        private Long logIdx;

        private String pcid;

        @JsonProperty("episode_id")
        private String episodeId;

        @JsonProperty("stb_id")
        private String stbId;

        @JsonProperty("play_start")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private String playStart;

        @JsonProperty("play_end")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private String playEnd;

        @JsonProperty("mac_address")
        private String macAddress;

        @JsonProperty("running")
        private boolean running;


        public User toEntity(){
            return User.builder()
                    .logIdx(logIdx)
                    .pcid(pcid)
                    .episodeId(episodeId)
                    .stbId(stbId)
                    .playStart(playStart)
                    .playEnd(playEnd)
                    .macAddress(macAddress)
                    .running(running)
                    .build();
        }
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        @JsonProperty("log_idx")
        private Long logIdx;

        @JsonProperty
        private String pcid;

        @JsonProperty("episode_id")
        private String episodeId;

        @JsonProperty("stb_id")
        private String stbId;

        @JsonProperty("play_start")
        private String playStart;

        @JsonProperty("play_end")
        private String playEnd;

        @JsonProperty("mac_address")
        private String macAddress;

        @JsonProperty("running")
        private boolean running;

        public Response(User user) {
            logIdx = user.getLogIdx();
            pcid = user.getPcid();
            episodeId = user.getEpisodeId();
            stbId = user.getStbId();
            playStart = user.getPlayStart();
            playEnd = user.getPlayEnd();
            macAddress = user.getMacAddress();
            running = user.isRunning();
        }
    }

}
