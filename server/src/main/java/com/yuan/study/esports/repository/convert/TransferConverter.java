package com.yuan.study.esports.repository.convert;

import com.yuan.study.esports.domain.Transfer;
import com.yuan.study.esports.repository.po.TransferPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferConverter {

    TransferConverter INSTANCE = Mappers.getMapper(TransferConverter.class);

    Transfer fromPO(TransferPO transferPO);

    TransferPO toPO(Transfer transfer);
}
