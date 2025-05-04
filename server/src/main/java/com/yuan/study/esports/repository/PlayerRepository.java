package com.yuan.study.esports.repository;

import com.yuan.study.esports.domain.Player;
import com.yuan.study.esports.repository.convert.PlayerConverter;
import com.yuan.study.esports.repository.mapper.PlayerMapper;
import com.yuan.study.esports.repository.po.PlayerPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerRepository {
    private final PlayerMapper playerMapper;

    public Optional<Player> query(Long id) {
        PlayerPO selected = playerMapper.selectById(id);
        if (Objects.isNull(selected)) {
            return Optional.empty();
        }
        return Optional.of(PlayerConverter.INSTANCE.fromPO(selected));
    }

    public void save(Player player) {
        playerMapper.insert(PlayerConverter.INSTANCE.toPO(player));
    }

    public void update(Player player) {
        playerMapper.updateById(PlayerConverter.INSTANCE.toPO(player));
    }

    public void delete(Long id) {
        playerMapper.deleteById(id);
    }
}
