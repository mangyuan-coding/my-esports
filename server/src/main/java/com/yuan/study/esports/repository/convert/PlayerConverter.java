package com.yuan.study.esports.repository.convert;

import com.yuan.study.esports.domain.Player;
import com.yuan.study.esports.repository.po.PlayerPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerConverter {

    PlayerConverter INSTANCE = Mappers.getMapper(PlayerConverter.class);

    Player fromPO(PlayerPO playerPO);

    PlayerPO toPO(Player player);
}
