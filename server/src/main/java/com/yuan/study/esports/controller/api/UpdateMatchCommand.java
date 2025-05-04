package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Club;
import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.domain.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMatchCommand {

    private String name;
    private Game game;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal prizePool;
    private String location;
    private String description;

    public void merge(Match existed) {
        existed.setName(this.name);
        existed.setGame(this.game);
        existed.setStartDate(this.startDate);
        existed.setEndDate(this.endDate);
        existed.setPrizePool(this.prizePool);
        existed.setLocation(this.location);
        existed.setDescription(this.description);
    }

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "status", ignore = true)
        @Mapping(target = "id", ignore = true)
        Match toMatch(UpdateMatchCommand command);
    }

    @Getter
    @AllArgsConstructor
    public static class Result {
        private Boolean existed;

        public static Result ofNone() {
            return new Result(false);
        }

        public static Result ofSuccess() {
            return new Result(true);
        }
    }
}
