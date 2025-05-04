package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.repository.po.TransferPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        Transfer from(TransferPO transferPO);
    }

    public static Transfer from(TransferPO transferPO) {
        return Converter.INSTANCE.from(transferPO);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Transfer {
        private String id;

        private String playerId;

        private String toClubId;

        private BigDecimal transferFee;

        private LocalDate transferDate;

        private String notes;
    }
}
