package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.repository.po.PlayerPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

public class PlayerQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        Player from(PlayerPO playerPO);
    }

    public static Player from(PlayerPO playerPO) {
        return Converter.INSTANCE.from(playerPO);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Player {
        private String id;

        private String name;

        private Game game;

        private String position;

        private String clubId;

        private LocalDate joinDate;

        private String bio;
    }
}
