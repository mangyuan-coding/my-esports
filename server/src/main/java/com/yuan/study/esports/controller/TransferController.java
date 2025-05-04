package com.yuan.study.esports.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.controller.api.Response;
import com.yuan.study.esports.controller.api.TransfersQuery;
import com.yuan.study.esports.repository.mapper.TransferMapper;
import com.yuan.study.esports.repository.po.TransferPO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfers")
public class TransferController {
    private final TransferMapper transferMapper;

    @GetMapping
    public Response<List<TransfersQuery.Transfer>> allTransfers() {
        List<TransferPO> transferPOS = transferMapper.selectList(new QueryWrapper<>());
        if (Objects.isNull(transferPOS) || transferPOS.isEmpty()) {
            return Response.ofSuccess(Collections.emptyList());
        }
        return Response.ofSuccess(TransfersQuery.from(transferPOS));
    }
}
