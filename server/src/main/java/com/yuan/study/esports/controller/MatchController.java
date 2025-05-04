package com.yuan.study.esports.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.controller.api.*;
import com.yuan.study.esports.repository.mapper.MatchMapper;
import com.yuan.study.esports.repository.po.MatchPO;
import com.yuan.study.esports.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {

    private final MatchMapper matchMapper;
    private final MatchService matchService;

    @GetMapping
    public Response<List<MatchesQuery.Match>> matches() {
        List<MatchPO> matchPOS = matchMapper.selectList(new QueryWrapper<>());
        if (Objects.isNull(matchPOS) || matchPOS.isEmpty()) {
            return Response.ofSuccess(Collections.emptyList());
        }
        return Response.ofSuccess(MatchesQuery.from(matchPOS));
    }

    @GetMapping("/{id}")
    public Response<MatchQuery.Match> match(@PathVariable("id") Long id) {
        MatchPO selected = matchMapper.selectOne(new QueryWrapper<MatchPO>().lambda().eq(MatchPO::getId, id));
        if (Objects.isNull(selected)) {
            return Response.ofSuccess();
        }
        return Response.ofSuccess(MatchQuery.from(selected));
    }

    @PostMapping
    public Response<Void> create(@RequestBody CreateMatchCommand command) {
        matchService.create(command);
        return Response.ofSuccess();
    }

    @PutMapping("/{id}")
    public Response<UpdateMatchCommand.Result> update(@PathVariable("id") Long id,
                                                       @RequestBody UpdateMatchCommand command) {
        return Response.ofSuccess(matchService.update(id, command));
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable("id") Long id) {
        matchService.delete(id);
        return Response.ofSuccess();
    }

    @PostMapping("/{id}/register")
    public Response<RegisterMatchCommand.Result> register(@PathVariable("id") Long id,
                                                          @RequestBody RegisterMatchCommand command) {
        return Response.ofSuccess(matchService.register(id, command));
    }
}
