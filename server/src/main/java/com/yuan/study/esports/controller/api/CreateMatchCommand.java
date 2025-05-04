package com.yuan.study.esports.controller.api;

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
public class CreateMatchCommand {

    private String name;
    private Game game;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal prizePool;
    private String location;
    private String description;

    public Match toMatch() {
        return Converter.INSTANCE.toMatch(this);
    }

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "status", ignore = true)
        @Mapping(target = "id", ignore = true)
        Match toMatch(CreateMatchCommand command);
    }

    @Getter
    @AllArgsConstructor
    public static class Result {
        private Boolean existed;

        public static Result ofExisted() {
            return new Result(true);
        }

        public static Result ofSuccess() {
            return new Result(false);
        }
    }
}
