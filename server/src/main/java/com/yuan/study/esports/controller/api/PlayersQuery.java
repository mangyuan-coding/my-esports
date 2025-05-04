package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.domain.Player;
import com.yuan.study.esports.repository.po.PlayerPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

public class PlayersQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        Player from(PlayerPO playerPO);
        List<Player> fromList(List<PlayerPO> playerPOS);
    }

    public static List<Player> from(List<PlayerPO> players) {
        return Converter.INSTANCE.fromList(players);
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
