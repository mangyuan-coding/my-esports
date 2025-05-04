package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.repository.po.MatchPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MatchesQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        Match from(MatchPO matchPO);

        List<Match> fromList(List<MatchPO> matchPOS);
    }

    public static List<Match> from(List<MatchPO> matchPOS) {
        return Converter.INSTANCE.fromList(matchPOS);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Match {
        private String id;
        private String name;
        private Game game;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private BigDecimal prizePool;
        private String location;
        private com.yuan.study.esports.domain.Match.Status status;
        private String description;
    }
}
