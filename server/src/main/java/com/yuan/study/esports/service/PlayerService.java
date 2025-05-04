package com.yuan.study.esports.service;

import com.yuan.study.esports.controller.api.CreatePlayerCommand;
import com.yuan.study.esports.controller.api.UpdatePlayerCommand;
import com.yuan.study.esports.domain.Player;
import com.yuan.study.esports.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public CreatePlayerCommand.Result create(CreatePlayerCommand command) {
        playerRepository.save(command.toPlayer());
        return CreatePlayerCommand.Result.ofSuccess();
    }

    public UpdatePlayerCommand.Result update(Long id, UpdatePlayerCommand command) {
        Optional<Player> queried = playerRepository.query(id);
        if (queried.isEmpty()) {
            return UpdatePlayerCommand.Result.ofNone();
        }
        Player existed = queried.get();
        command.merge(existed);
        playerRepository.update(existed);
        return UpdatePlayerCommand.Result.ofSuccess();
    }

    public void delete(Long id) {
        playerRepository.delete(id);
    }
}
