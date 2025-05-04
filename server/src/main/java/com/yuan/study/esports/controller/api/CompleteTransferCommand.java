package com.yuan.study.esports.controller.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CompleteTransferCommand {

    @Getter
    @AllArgsConstructor
    public static class Result {
        private Boolean existed;

        public static Result ofExisted() {
            return new Result(true);
        }

        public static Result ofSuccess() {
            return new Result(false);
        }
    }
}
