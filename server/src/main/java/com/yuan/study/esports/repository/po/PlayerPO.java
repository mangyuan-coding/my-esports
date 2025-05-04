package com.yuan.study.esports.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuan.study.esports.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_player")
public class PlayerPO {
    @TableId
    private Long id;

    private String name;

    private Game game;

    private String position;

    private Long clubId;

    private LocalDate joinDate;

    private String bio;
}
