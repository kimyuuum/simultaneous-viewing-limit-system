package com.skb.checkservice.domain.WatchInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WatchInfoDto {

    @Getter
    public static class Request{

        @JsonProperty
        private String pcid;

        private String episodeId;

        private String stbId;

        private String macAddress;

        private String playStart;

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
