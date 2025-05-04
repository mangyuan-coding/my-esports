package com.yuan.study.esports.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.controller.api.*;
import com.yuan.study.esports.repository.mapper.PlayerMapper;
import com.yuan.study.esports.repository.po.PlayerPO;
import com.yuan.study.esports.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {

    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    @GetMapping
    public Response<List<PlayersQuery.Player>> players() {
        List<PlayerPO> playerPOS = playerMapper.selectList(new QueryWrapper<>());
        if (Objects.isNull(playerPOS) || playerPOS.isEmpty()) {
            return Response.ofSuccess(Collections.emptyList());
        }
        return Response.ofSuccess(PlayersQuery.from(playerPOS));
    }

    @GetMapping("/{id}")
    public Response<PlayerQuery.Player> player(@PathVariable("id") Long id) {
        PlayerPO playerPOS = playerMapper.selectOne(new QueryWrapper<PlayerPO>().lambda()
                .eq(PlayerPO::getId, id));
        if (Objects.isNull(playerPOS)) {
            return Response.ofSuccess();
        }
        return Response.ofSuccess(PlayerQuery.from(playerPOS));
    }

    @PostMapping
    public Response<CreatePlayerCommand.Result> create(@RequestBody CreatePlayerCommand command) {
        return Response.ofSuccess(playerService.create(command));
    }

    @PutMapping("/{id}")
    public Response<UpdatePlayerCommand.Result> update(@PathVariable("id") Long id,
                                                         @RequestBody UpdatePlayerCommand command) {
        return Response.ofSuccess(playerService.update(id, command));
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable("id") Long id) {
        playerService.delete(id);
        return Response.ofSuccess();
    }
}
