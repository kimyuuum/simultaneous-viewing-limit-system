package com.skb.pushservice.domain.WatchInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WatchInfo {

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

    @JsonProperty("play_end")
    private String playEnd;

    @JsonProperty
    private boolean running;

}
