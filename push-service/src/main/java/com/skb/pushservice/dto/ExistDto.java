package com.skb.pushservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class ExistDto {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response{

        @JsonProperty("new_user")
        private String newUser;

        @JsonProperty("exist_user")
        private String existUser;

    }

}
