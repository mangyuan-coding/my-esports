package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.repository.po.ClubPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

public class ClubQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        Club from(ClubPO clubPO);
    }

    public static Club from(ClubPO clubPO) {
        return Converter.INSTANCE.from(clubPO);
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
    }
}
