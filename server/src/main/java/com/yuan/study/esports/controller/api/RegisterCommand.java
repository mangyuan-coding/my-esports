package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Account;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCommand {

    private String username;

    private String password;

    private String email;

    public Account toAccount() {
        return Converter.INSTANCE.toAccount(this);
    }

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "isAdmin", ignore = true)
        @Mapping(target = "id", ignore = true)
        Account toAccount(RegisterCommand command);
    }

    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {

        private Boolean existAccount;

        private Boolean errorPassword;

        public static Result ofExistAccount() {
            return Result.builder().existAccount(true).build();
        }

        public static Result ofSuccess() {
            return Result.builder().existAccount(false).build();
        }
    }
}
