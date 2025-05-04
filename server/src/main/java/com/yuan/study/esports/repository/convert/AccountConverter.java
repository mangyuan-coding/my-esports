package com.yuan.study.esports.repository.convert;

import com.yuan.study.esports.domain.Account;
import com.yuan.study.esports.repository.po.AccountPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountConverter {
    AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

    Account fromPO(AccountPO accountPO);

    AccountPO toPO(Account account);
}
