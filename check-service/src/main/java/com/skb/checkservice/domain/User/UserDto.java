package com.skb.checkservice.domain.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDto {

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response{

        private String newUser;

        private String existUser;
    }

}
