package com.skb.pushservice.domain.WatchInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WatchInfoDto {

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request{

        @JsonProperty
        private String pcid;

        private String episodeId;

        private String stbId;

        private String playStart;

        private String macAddress;

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
