package com.skb.pushservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private String pcid;

    private String content;
}
