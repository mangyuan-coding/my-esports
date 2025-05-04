package com.yuan.study.esports.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yuan.study.esports.controller.api.LoginCommand;
import com.yuan.study.esports.controller.api.LogoutCommand;
import com.yuan.study.esports.controller.api.RegisterCommand;
import com.yuan.study.esports.domain.Account;
import com.yuan.study.esports.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;
    private final Algorithm rsa256;

    public LoginCommand.Result login(LoginCommand command) {
        Optional<Account> account = accountRepository.queryByUsername(command.getUsername());
        if (account.isEmpty()) {
            return LoginCommand.Result.ofNoneAccount();
        }
        if (Objects.equals(command.getPassword(), account.get().getPassword())) {
            return LoginCommand.Result.of(account.get(), JWT.create().sign(rsa256));
        }
        return LoginCommand.Result.ofErrorPassword();
    }

    @Transactional
    public RegisterCommand.Result register(RegisterCommand command) {
        Optional<Account> account = accountRepository.queryByUsername(command.getUsername());
        if (account.isPresent()) {
            return RegisterCommand.Result.ofExistAccount();
        }
        accountRepository.save(command.toAccount());
        return RegisterCommand.Result.ofSuccess();
    }

    public void logout(LogoutCommand command) {
        
    }
}
