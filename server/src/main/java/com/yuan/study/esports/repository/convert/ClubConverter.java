package com.yuan.study.esports.repository.convert;

import com.yuan.study.esports.domain.Club;
import com.yuan.study.esports.repository.po.ClubPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClubConverter {

    ClubConverter INSTANCE = Mappers.getMapper(ClubConverter.class);

    Club fromPO(ClubPO clubPO);

    ClubPO toPO(Club club);
}
