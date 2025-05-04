package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.repository.po.ClubPO;
import com.yuan.study.esports.repository.po.PlayerPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

public class ClubNumbersQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        ClubNumber from(PlayerPO playerPO);

        List<ClubNumber> fromList(List<PlayerPO> playerPOS);
    }

    public static List<ClubNumber> from(List<PlayerPO> playerPOS) {
        return Converter.INSTANCE.fromList(playerPOS);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubNumber {
        private Long id;

        private String name;

        private Game game;

        private String position;

        private LocalDate joinDate;
    }
}
