package com.skb.checkservice.domain.WatchInfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;


public class WatchInfoDto {

    @Getter
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Request {

        private String pcid;

        private String episodeId;

        private String stbId;

        private String macAddress;

        private String playStart;

        private String playEnd;

        private boolean running;

        public WatchInfo toEntity() {
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
