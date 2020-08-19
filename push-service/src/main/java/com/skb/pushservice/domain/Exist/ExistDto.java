package com.skb.pushservice.domain.Exist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skb.pushservice.domain.WatchInfo.WatchInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ExistDto {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request{

        @JsonProperty("new_user")
        private String newUser;

        @JsonProperty("exist_user")
        private String existUser;

        @JsonProperty("watch_info")
        private WatchInfoDto.Request watchInfo;

    }

    @Getter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response{

        @JsonProperty("new_user")
        private String newUser;

        @JsonProperty("exist_user")
        private String existUser;

        public Response(String dtoNewUser,
                        String dtoExistUser){
            newUser = dtoNewUser;
            existUser = dtoExistUser;
        }

    }

}
