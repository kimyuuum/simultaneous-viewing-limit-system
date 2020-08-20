package com.skb.pushservice.domain.WatchInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WatchInfo {

    @JsonProperty
    private String pcid;

    private String episodeId;

    private String stbId;

    private String playStart;

    private String macAddress;

    private String playEnd;

    private boolean running;

}
