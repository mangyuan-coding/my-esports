package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.domain.Club;
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
public class CreateClubCommand {

    private String name;

    private String location;

    private String coach;

    private LocalDate establishedDate;

    public Club toClub() {
        return Converter.INSTANCE.toClub(this);
    }

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(target = "id", ignore = true)
        Club toClub(CreateClubCommand command);
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
