package com.yuan.study.esports.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.controller.api.*;
import com.yuan.study.esports.repository.mapper.ClubMapper;
import com.yuan.study.esports.repository.mapper.PlayerMapper;
import com.yuan.study.esports.repository.po.ClubPO;
import com.yuan.study.esports.repository.po.PlayerPO;
import com.yuan.study.esports.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubs")
public class ClubController {

    private final ClubMapper clubMapper;
    private final ClubService clubService;
    private final PlayerMapper playerMapper;

    @GetMapping
    public Response<List<ClubsQuery.Club>> clubs() {
        List<ClubPO> clubPOS = clubMapper.selectList(new QueryWrapper<>());
        if (Objects.isNull(clubPOS) || clubPOS.isEmpty()) {
            return Response.ofSuccess(Collections.emptyList());
        }
        return Response.ofSuccess(ClubsQuery.from(clubPOS));
    }

    @GetMapping("/{id}")
    public Response<ClubQuery.Club> club(@PathVariable("id") Long id) {
        ClubPO selected = clubMapper.selectOne(new QueryWrapper<ClubPO>().lambda().eq(ClubPO::getId, id));
        if (Objects.isNull(selected)) {
            return Response.ofSuccess();
        }
        return Response.ofSuccess(ClubQuery.from(selected));
    }

    @PostMapping
    public Response<CreateClubCommand.Result> create(@RequestBody CreateClubCommand command) {
        return Response.ofSuccess(clubService.create(command));
    }

    @PutMapping("/{id}")
    public Response<UpdateClubCommand.Result> update(@PathVariable("id") Long id,
                                                     @RequestBody UpdateClubCommand command) {
        return Response.ofSuccess(clubService.update(id, command));
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable("id") Long id) {
        clubService.delete(id);
        return Response.ofSuccess();
    }

    @GetMapping("/{id}/numbers")
    public Response<List<ClubNumbersQuery.ClubNumber>> clubNumbers(@PathVariable("id") Long id) {
        List<PlayerPO> playerPOS = playerMapper
                .selectList(new QueryWrapper<PlayerPO>().lambda().eq(PlayerPO::getClubId, id));
        if (Objects.isNull(playerPOS) || playerPOS.isEmpty()) {
            return Response.ofSuccess();
        }
        return Response.ofSuccess(ClubNumbersQuery.from(playerPOS));
    }
}
