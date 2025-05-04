package com.yuan.study.esports.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.domain.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_match")
public class MatchPO {
    @TableId
    private Long id;
    private String name;
    private Game game;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal prizePool;
    private String location;
    private Match.Status status;
    private String description;
}
