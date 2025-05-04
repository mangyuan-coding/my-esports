package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.repository.po.ClubPO;
import com.yuan.study.esports.repository.po.PlayerPO;
import com.yuan.study.esports.repository.po.TransferPO;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransfersQuery {

    public static List<Transfer> from(List<TransferPO> transferPOS, List<PlayerPO> playerPOS,
                                      List<ClubPO> clubPOS) {
        List<Transfer> transfers = new ArrayList<>();
        Map<Long, PlayerPO> playerPOMap = Objects.isNull(playerPOS) || playerPOS.isEmpty() ? Collections.emptyMap() :
                playerPOS.stream().collect(Collectors.toMap(PlayerPO::getId, Function.identity(), (a, b) -> a));
        Map<Long, ClubPO> clubPOMap = Objects.isNull(clubPOS) || clubPOS.isEmpty() ? Collections.emptyMap() :
                clubPOS.stream().collect(Collectors.toMap(ClubPO::getId, Function.identity(), (a, b) -> a));
        for (TransferPO transfer : transferPOS) {
            PlayerPO player;
            ClubPO fromClub;
            ClubPO toClub;
            if (clubPOMap.containsKey(transfer.getToClubId())
                    && playerPOMap.containsKey(transfer.getPlayerId())
                    && clubPOMap.containsKey((player = playerPOMap.get(transfer.getPlayerId())).getClubId())) {
                fromClub = clubPOMap.get(player.getClubId());
                toClub = clubPOMap.get(transfer.getToClubId());
                transfers.add(Transfer.builder()
                        .id(String.valueOf(transfer.getId()))
                        .player(Player.builder()
                                .id(String.valueOf(player.getId()))
                                .name(player.getName())
                                .build())
                        .fromClub(Club.builder()
                                .id(String.valueOf(fromClub.getId()))
                                .name(fromClub.getName())
                                .build())
                        .toClub(Club.builder()
                                .id(String.valueOf(toClub.getId()))
                                .name(toClub.getName())
                                .build())
                        .status(transfer.getStatus())
                        .transferFee(transfer.getTransferFee())
                        .build());
            }
        }
        return transfers;
    }

    @Setter
    @Getter
    @Builder
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
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Player {
        private String id;
        private String name;
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
