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

public class MatchQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        Match from(MatchPO matchPO);
    }

    public static Match from(MatchPO matchPO) {
        return Converter.INSTANCE.from(matchPO);
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
