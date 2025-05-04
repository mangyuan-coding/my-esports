package com.yuan.study.esports.controller.api;

import com.yuan.study.esports.repository.po.ClubPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

public class ClubsQuery {

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        Club from(ClubPO clubPO);

        List<Club> fromList(List<ClubPO> clubPOS);
    }

    public static List<Club> from(List<ClubPO> clubPOS) {
        return Converter.INSTANCE.fromList(clubPOS);
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
