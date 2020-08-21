package com.skb.pushservice.domain.WatchInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

public class WatchInfoDto {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Request {

        private String pcid;

        private String episodeId;

        private String stbId;

        private String playStart;

        private String macAddress;

        private String playEnd;

        private boolean running;

    }

}
