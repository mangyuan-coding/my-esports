package com.yuan.study.esports.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.study.esports.domain.Club;
import com.yuan.study.esports.repository.convert.ClubConverter;
import com.yuan.study.esports.repository.mapper.ClubMapper;
import com.yuan.study.esports.repository.po.ClubPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClubRepository {

    private final ClubMapper clubMapper;

    public Boolean existedByName(String name) {
        ClubPO existed = clubMapper.selectOne(new QueryWrapper<ClubPO>().lambda().eq(ClubPO::getName, name));
        return Objects.nonNull(existed);
    }

    public Optional<Club> query(Long id) {
        ClubPO selected = clubMapper.selectById(id);
        if (Objects.isNull(selected)) {
            return Optional.empty();
        }
        return Optional.of(ClubConverter.INSTANCE.fromPO(selected));
    }

    public void save(Club club) {
        clubMapper.insert(ClubConverter.INSTANCE.toPO(club));
    }

    public void update(Club club) {
        clubMapper.updateById(ClubConverter.INSTANCE.toPO(club));
    }

    public void delete(Long id) {
        clubMapper.deleteById(id);
    }
}
