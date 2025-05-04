package com.yuan.study.esports.controller.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuan.study.esports.domain.Account;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginCommand {

    private String username;

    private String password;

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "token", ignore = true)
        @Mapping(target = "hasAccount", ignore = true)
        @Mapping(target = "errorPassword", ignore = true)
        Result fromAccount(Account account);
    }

    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        @JsonIgnore
        private Boolean hasAccount;
        @JsonIgnore
        private Boolean errorPassword;

        private String token;

        private Long id;

        private String username;

        private Boolean isAdmin;

        public static Result ofNoneAccount() {
            return Result.builder().hasAccount(false).build();
        }

        public static Result of(Account account, String token) {
            Result result = Converter.INSTANCE.fromAccount(account);
            result.setHasAccount(true);
            result.setErrorPassword(false);
            result.setToken(token);
            return result;
        }

        public static Result ofErrorPassword() {
            return Result.builder().hasAccount(true).errorPassword(true).build();
        }
    }
}
