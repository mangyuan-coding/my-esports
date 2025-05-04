package com.yuan.study.esports.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.domain.Match;
import com.yuan.study.esports.repository.convert.MatchConverter;
import com.yuan.study.esports.repository.mapper.MatchMapper;
import com.yuan.study.esports.repository.mapper.RegisterRecordMapper;
import com.yuan.study.esports.repository.po.MatchPO;
import com.yuan.study.esports.repository.po.RegisterRecordPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatchRepository {

    private final MatchMapper matchMapper;
    private final RegisterRecordMapper registerRecordMapper;

    public void save(Match match) {
        matchMapper.insert(MatchConverter.INSTANCE.toPO(match));
    }

    public Optional<Match> query(Long id) {
        MatchPO existed = matchMapper.selectById(id);
        if (Objects.isNull(existed)) {
            return Optional.empty();
        }
        return Optional.of(MatchConverter.INSTANCE.fromPO(existed));
    }

    public void update(Match match) {
        matchMapper.updateById(MatchConverter.INSTANCE.toPO(match));
    }

    public void delete(Long id) {
        matchMapper.deleteById(id);
    }

    public Boolean registered(Long matchId, Long clubId) {
        return registerRecordMapper.selectCount(new QueryWrapper<RegisterRecordPO>().lambda()
                .eq(RegisterRecordPO::getMatchId, matchId)
                .eq(RegisterRecordPO::getClubId, clubId)) > 0;
    }

    public void register(Long matchId, Long clubId) {
        registerRecordMapper.insert(RegisterRecordPO.builder()
                .matchId(matchId)
                .clubId(clubId)
                .registerDate(LocalDateTime.now())
                .build());
    }
}
