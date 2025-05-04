package com.yuan.study.esports.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.controller.api.*;
import com.yuan.study.esports.repository.mapper.ClubMapper;
import com.yuan.study.esports.repository.mapper.PlayerMapper;
import com.yuan.study.esports.repository.mapper.TransferMapper;
import com.yuan.study.esports.repository.po.ClubPO;
import com.yuan.study.esports.repository.po.PlayerPO;
import com.yuan.study.esports.repository.po.TransferPO;
import com.yuan.study.esports.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfers")
public class TransferController {
    private final TransferMapper transferMapper;
    private final ClubMapper clubMapper;
    private final PlayerMapper playerMapper;
    private final TransferService transferService;

    @GetMapping
    public Response<List<TransfersQuery.Transfer>> allTransfers() {
        List<TransferPO> transferPOS = transferMapper.selectList(new QueryWrapper<>());
        if (Objects.isNull(transferPOS) || transferPOS.isEmpty()) {
            return Response.ofSuccess(Collections.emptyList());
        }
        Set<Long> clubIds = new HashSet<>();
        Set<Long> playerIds = new HashSet<>();
        for (TransferPO transferPO : transferPOS) {
            playerIds.add(transferPO.getPlayerId());
            clubIds.add(transferPO.getToClubId());
        }
        List<PlayerPO> playerPOS = playerMapper.selectByIds(playerIds);
        for (PlayerPO playerPO : playerPOS) {
            clubIds.add(playerPO.getClubId());
        }
        List<ClubPO> clubPOS = clubMapper.selectByIds(clubIds);
        return Response.ofSuccess(TransfersQuery.from(transferPOS, playerPOS, clubPOS));
    }

    @GetMapping("/{id}")
    public Response<TransferQuery.Transfer> transfer(@PathVariable("id") Long id) {
        TransferPO transferPO = transferMapper.selectOne(new QueryWrapper<TransferPO>().lambda()
                .eq(TransferPO::getId, id));
        if (Objects.isNull(transferPO)) {
            return Response.ofSuccess();
        }
        return Response.ofSuccess(TransferQuery.from(transferPO));
    }

    @PostMapping
    public Response<SubmitTransferCommand.Result> submit(@RequestBody SubmitTransferCommand command) {
        return Response.ofSuccess(transferService.submit(command));
    }

    @PatchMapping("/{id}/review")
    public Response<ReviewTransferCommand.Result> review(@PathVariable("id") Long id,
                                                         @RequestBody ReviewTransferCommand command) {
        return Response.ofSuccess(transferService.review(id, command));
    }

    @PatchMapping("/{id}/complete")
    public Response<CompleteTransferCommand.Result> complete(@PathVariable("id") Long id) {
        return Response.ofSuccess(transferService.complete(id));
    }

    @GetMapping("/player/{playerId}")
    public Response<List<PlayerTransferQuery.Transfer>> playerTransfers(@PathVariable("playerId") Long playerId) {
        List<TransferPO> transferPOS = transferMapper.selectList(new QueryWrapper<TransferPO>().lambda()
                .eq(TransferPO::getPlayerId, playerId));
        if (Objects.isNull(transferPOS) || transferPOS.isEmpty()) {
            return Response.ofSuccess(Collections.emptyList());
        }
        return Response.ofSuccess(PlayerTransferQuery.from(transferPOS));
    }
}
