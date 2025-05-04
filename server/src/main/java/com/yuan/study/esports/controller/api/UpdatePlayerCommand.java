package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlayerCommand {

    private String name;

    private Game game;

    private String position;

    private Long clubId;

    private LocalDate joinDate;

    private String bio;

    public Player toPlayer() {
        return Converter.INSTANCE.toPlayer(this);
    }

    public void merge(Player existed) {
        existed.setName(this.name);
        existed.setGame(this.game);
        existed.setPosition(this.position);
        existed.setClubId(this.clubId);
        existed.setJoinDate(this.joinDate);
        existed.setBio(this.getBio());
    }

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "id", ignore = true)
        Player toPlayer(UpdatePlayerCommand command);
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
