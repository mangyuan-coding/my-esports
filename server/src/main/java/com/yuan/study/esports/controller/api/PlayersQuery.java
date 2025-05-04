package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.repository.po.ClubPO;
import com.yuan.study.esports.repository.po.PlayerPO;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayersQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "club", ignore = true)
        Player from(PlayerPO playerPO);
    }

    public static List<Player> from(List<PlayerPO> playerPOS, List<ClubPO> clubPOS) {
        Map<Long, ClubPO> clubPOMap = Objects.isNull(clubPOS) || clubPOS.isEmpty() ? Collections.emptyMap() :
                clubPOS.stream().collect(Collectors.toMap(ClubPO::getId, Function.identity(), (a, b) -> a));
        List<Player> players = new ArrayList<>();
        for (PlayerPO playerPO : playerPOS) {
            Player player = Converter.INSTANCE.from(playerPO);
            if (clubPOMap.containsKey(playerPO.getClubId())) {
                player.setClub(Club.builder()
                        .id(String.valueOf(playerPO.getClubId()))
                        .name(clubPOMap.get(playerPO.getClubId()).getName())
                        .build());
            }
            players.add(player);
        }
        return players;
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

        private Club club;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Club {
        private String id;
        private String name;
    }
}
