package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewTransferCommand {

    private Transfer.Status status;

    @Getter
    @AllArgsConstructor
    public static class Result {
        private Boolean existed;
        private Boolean hasReviewed;

        public static Result ofNone() {
            return new Result(false, false);
        }

        public static Result ofHasReviewed() {
            return new Result(false, true);
        }

        public static Result ofSuccess() {
            return new Result(true, false);
        }
    }
}
