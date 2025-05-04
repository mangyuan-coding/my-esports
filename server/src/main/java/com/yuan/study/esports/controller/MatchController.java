package com.yuan.study.esports.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.controller.api.ClubsQuery;
import com.yuan.study.esports.controller.api.MatchesQuery;
import com.yuan.study.esports.controller.api.Response;
import com.yuan.study.esports.repository.mapper.MatchMapper;
import com.yuan.study.esports.repository.po.ClubPO;
import com.yuan.study.esports.repository.po.MatchPO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {

    private final MatchMapper matchMapper;

    @GetMapping
    public Response<List<MatchesQuery.Match>> matches() {
        List<MatchPO> matchPOS = matchMapper.selectList(new QueryWrapper<>());
        if (Objects.isNull(matchPOS) || matchPOS.isEmpty()) {
            return Response.ofSuccess(Collections.emptyList());
        }
        return Response.ofSuccess(MatchesQuery.from(matchPOS));
    }


}
