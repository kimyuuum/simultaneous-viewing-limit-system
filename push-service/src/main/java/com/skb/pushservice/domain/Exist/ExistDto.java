package com.skb.pushservice.domain.Exist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.skb.pushservice.domain.WatchInfo.WatchInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ExistDto {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Request{

        private String newUser;

        private String existUser;

        private WatchInfoDto.Request watchInfo;

    }

    @Getter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Response{

        private String newUser;

        private String existUser;

        public Response(String dtoNewUser, String dtoExistUser){
            newUser = dtoNewUser;
            existUser = dtoExistUser;
        }

    }

}
