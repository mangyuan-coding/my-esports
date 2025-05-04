package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.repository.po.TransferPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

public class PlayerTransferQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "toClub", ignore = true)
        @Mapping(target = "player", ignore = true)
        @Mapping(target = "fromClub", ignore = true)
        Transfer from(TransferPO playerPO);

        List<Transfer> fromList(List<TransferPO> transferPOS);
    }

    public static List<Transfer> from(List<TransferPO> playerPOS) {
        return Converter.INSTANCE.fromList(playerPOS);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Transfer {
        private String id;

        private Player player;

        private Club fromClub;

        private Club toClub;

        private BigDecimal transferFee;

        private com.yuan.study.esports.domain.Transfer.Status status;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Player {
        private Long id;
        private String name;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Club {
        private Long id;
        private String name;
    }
}
