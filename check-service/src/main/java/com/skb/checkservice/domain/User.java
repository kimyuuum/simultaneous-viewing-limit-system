package com.skb.checkservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class User implements Serializable {

    private String pcid;

    private String episodeId;

    private String stbId;

    private String playStart;

    private String playEnd;

    private String macAddress;

    private boolean running;
    //in mysql TINYINT (true = 1 false = 0)

}
