package com.skb.checkservice.domain.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    public static class Response{

        @JsonProperty("new_user")
        private String newUser;

        @JsonProperty("exist_user")
        private String existUser;
    }

}
