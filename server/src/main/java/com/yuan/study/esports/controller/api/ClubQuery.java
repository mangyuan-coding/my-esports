package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.repository.po.ClubPO;
import com.yuan.study.esports.repository.po.PlayerPO;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ClubQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "players", ignore = true)
        @Mapping(target = "description", ignore = true)
        Club from(ClubPO clubPO);
        Player from(PlayerPO playerPO);
        List<Player> fromList(List<PlayerPO> playerPOS);
    }

    public static Club from(ClubPO clubPO, List<PlayerPO> playerPOS) {
        Club club = Converter.INSTANCE.from(clubPO);
        if (Objects.nonNull(playerPOS) && !playerPOS.isEmpty()) {
            club.setPlayers(Converter.INSTANCE.fromList(playerPOS));
        }
        return club;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Club {
        private String id;

        private String name;

        private String location;

        private String coach;

        private LocalDate establishedDate;

        private String description;

        private List<Player> players;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Player {
        private String id;

        private String name;

        private Game game;

        private String position;

        private LocalDateTime joinDate;
    }
}
