package com.example.my.dto;

import com.example.my.domain.User;
import lombok.*;

public class UserDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginReqDto{
        private String username;
        private String password;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResDto{
        String refreshToken;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqUser{
        private String username;
        private String password;

        public User toEntity(){
            return User.of(getUsername(), getPassword());
        }

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResUser{
        private Long id;
    }

}
