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

        @JsonProperty("play_start")
        private String playStart;

        @JsonProperty("mac_address")
        private String macAddress;

        public WatchInfo toEntity(){
            return WatchInfo.builder()
                    .pcid(pcid)
                    .episodeId(episodeId)
                    .stbId(stbId)
                    .playStart(playStart)
                    .macAddress(macAddress)
                    .build();
        }
    }

}
