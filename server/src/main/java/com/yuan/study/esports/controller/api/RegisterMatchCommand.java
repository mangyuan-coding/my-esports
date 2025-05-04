package com.yuan.study.esports.controller.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMatchCommand {

    private Long clubId;

    @Getter
    @AllArgsConstructor
    public static class Result {
        private Boolean none;
        private Boolean registered;
        private Boolean invalid;

        public static Result ofNone() {
            return new Result(true, false, true);
        }

        public static Result ofInvalid() {
            return new Result(true, false, true);
        }

        public static Result ofRegistered() {
            return new Result(true, true, true);
        }

        public static Result ofSuccess() {
            return new Result(false, false, false);
        }
    }
}
