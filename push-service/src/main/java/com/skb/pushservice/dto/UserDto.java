package com.skb.pushservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skb.pushservice.domain.User;
import lombok.Getter;

public class UserDto {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request{
        @JsonProperty
        private String pcid;

        @JsonProperty
        private String content;


    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        @JsonProperty
        private String pcid;

        @JsonProperty
        private String content;

        public Response(User user) {
            pcid = user.getPcid();
            content = user.getContent();
        }
    }

}
