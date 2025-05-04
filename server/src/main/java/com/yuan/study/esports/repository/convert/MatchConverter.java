package com.yuan.study.esports.repository.convert;

import com.yuan.study.esports.domain.Match;
import com.yuan.study.esports.repository.po.MatchPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchConverter {

    MatchConverter INSTANCE = Mappers.getMapper(MatchConverter.class);

    Match fromPO(MatchPO matchPO);

    MatchPO toPO(Match match);
}
