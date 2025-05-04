package com.yuan.study.esports.service;

import com.yuan.study.esports.controller.api.CreateMatchCommand;
import com.yuan.study.esports.controller.api.RegisterMatchCommand;
import com.yuan.study.esports.controller.api.UpdateMatchCommand;
import com.yuan.study.esports.domain.Match;
import com.yuan.study.esports.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public void create(CreateMatchCommand command) {
        matchRepository.save(command.toMatch());
    }

    @Transactional
    public UpdateMatchCommand.Result update(Long id, UpdateMatchCommand command) {
        Optional<Match> queried = matchRepository.query(id);
        if (queried.isEmpty()) {
            return UpdateMatchCommand.Result.ofNone();
        }
        Match match = queried.get();
        command.merge(match);
        matchRepository.update(match);
        return UpdateMatchCommand.Result.ofSuccess();
    }

    public void delete(Long id) {
        matchRepository.delete(id);
    }

    @Transactional
    public RegisterMatchCommand.Result register(Long id, RegisterMatchCommand command) {
        Optional<Match> queried = matchRepository.query(id);
        if (queried.isEmpty()) {
            return RegisterMatchCommand.Result.ofNone();
        }
        Match match = queried.get();

        if (!match.canRegister()) {
            return RegisterMatchCommand.Result.ofInvalid();
        }
        if (matchRepository.registered(id, command.getClubId())) {
            return RegisterMatchCommand.Result.ofRegistered();
        }
        matchRepository.register(id, command.getClubId());
        return RegisterMatchCommand.Result.ofSuccess();
    }
}
