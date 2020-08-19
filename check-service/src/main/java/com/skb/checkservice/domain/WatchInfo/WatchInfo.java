package com.skb.checkservice.domain.WatchInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@RedisHash("user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WatchInfo implements Serializable {

    private String pcid;

    private String episodeId;

    private String stbId;

    private String playStart;

    private String macAddress;

    private String playEnd;

    @JsonProperty
    private boolean running;

}