package com.yuan.study.esports.repository.convert;

import com.yuan.study.esports.domain.RegisterRecord;
import com.yuan.study.esports.repository.po.RegisterRecordPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterRecordConverter {

    RegisterRecordConverter INSTANCE = Mappers.getMapper(RegisterRecordConverter.class);

    RegisterRecord fromPO(RegisterRecordPO registerRecordPO);

    @Mapping(target = "id", ignore = true)
    RegisterRecordPO toPO(RegisterRecord record);
}
