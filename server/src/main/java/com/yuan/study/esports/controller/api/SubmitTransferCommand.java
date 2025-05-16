package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTransferCommand {

    private Long playerId;
    private Long toClubId;
    private BigDecimal transferFee;
    private LocalDate transferDate;
    private String notes;

    public Transfer toTransfer() {
        Transfer transfer = Converter.INSTANCE.toTransfer(this);
        transfer.setStatus(Transfer.Status.pending);
        return transfer;
    }

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "status", ignore = true)
        @Mapping(target = "id", ignore = true)
        Transfer toTransfer(SubmitTransferCommand command);
    }

    @Getter
    @AllArgsConstructor
    public static class Result {

        private Boolean existed;

        private Boolean playerNotExist;

        private Boolean applyOwnClub;

        public static Result ofExisted() {
            return new Result(true, true, true);
        }

        public static Result ofPlayerNotExist() {
            return new Result(false, true, false);
        }

        public static Result ofApplyOwnClub() {
            return new Result(false, false, true);
        }

        public static Result ofSuccess() {
            return new Result(false, false, false);
        }
    }
}
