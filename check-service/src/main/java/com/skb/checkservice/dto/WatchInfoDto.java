package com.skb.checkservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skb.checkservice.domain.WatchInfo;
import lombok.Getter;

public class WatchInfoDto {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request{

        @JsonProperty
        private String pcid;

        @JsonProperty("episode_id")
        private String episodeId;

        @JsonProperty("stb_id")
        private String stbId;

        @JsonProperty("mac_address")
        private String macAddress;

        @JsonProperty("play_start")
        private String playStart;

        @JsonProperty("play_end")
        private String playEnd;

        @JsonProperty
        private boolean running;

        public WatchInfo toEntity(){
            return WatchInfo.builder()
                    .pcid(pcid)
                    .episodeId(episodeId)
                    .stbId(stbId)
                    .macAddress(macAddress)
                    .playStart(playStart)
                    .playEnd(playEnd)
                    .running(running)
                    .build();
        }
    }

}
