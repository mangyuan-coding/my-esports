package com.yuan.study.esports.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.domain.Account;
import com.yuan.study.esports.repository.convert.AccountConverter;
import com.yuan.study.esports.repository.mapper.AccountMapper;
import com.yuan.study.esports.repository.po.AccountPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountRepository {

    private final AccountMapper accountMapper;

    public Optional<Account> queryByUsername(String username) {
        AccountPO exist = accountMapper
                .selectOne(new QueryWrapper<AccountPO>().lambda().eq(AccountPO::getUsername, username));
        if (Objects.isNull(exist)) {
            return Optional.empty();
        }
        return Optional.of(AccountConverter.INSTANCE.fromPO(exist));
    }

    public void save(Account account) {
        accountMapper.insert(AccountConverter.INSTANCE.toPO(account));
    }
}
