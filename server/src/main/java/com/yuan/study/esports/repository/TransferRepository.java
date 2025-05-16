package com.yuan.study.esports.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.domain.Transfer;
import com.yuan.study.esports.repository.convert.TransferConverter;
import com.yuan.study.esports.repository.mapper.TransferMapper;
import com.yuan.study.esports.repository.po.TransferPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransferRepository {

    private final TransferMapper transferMapper;

    public boolean existUnfinishedByPlayerId(Long playerId) {
        List<TransferPO> transferPOS = transferMapper
                .selectList(new QueryWrapper<TransferPO>().lambda()
                        .eq(TransferPO::getPlayerId, playerId));
        return false;
    }

    public void save(Transfer transfer) {
        transferMapper.insert(TransferConverter.INSTANCE.toPO(transfer));
    }

    public Optional<Transfer> query(Long id) {
        TransferPO transferPO = transferMapper.selectById(id);
        if (Objects.isNull(transferPO)) {
            return Optional.empty();
        }
        return Optional.of(TransferConverter.INSTANCE.fromPO(transferPO));
    }

    public Boolean existPendingByPlayerId(Long playId) {
        Long pending = transferMapper.selectCount(new QueryWrapper<TransferPO>().lambda()
                .eq(TransferPO::getStatus, Transfer.Status.pending)
                .eq(TransferPO::getPlayerId, playId));
        return Objects.nonNull(pending) && pending > 0;
    }

    public void update(Transfer transfer) {
        transferMapper.updateById(TransferConverter.INSTANCE.toPO(transfer));
    }
}
