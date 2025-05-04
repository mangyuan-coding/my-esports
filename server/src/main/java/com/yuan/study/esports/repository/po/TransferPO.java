package com.yuan.study.esports.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuan.study.esports.domain.Game;
import com.yuan.study.esports.domain.Transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_transfer")
public class TransferPO {
    @TableId
    private Long id;

    private Long playerId;

    private Long toClubId;

    private Transfer.Status status;

    private BigDecimal transferFee;

    private LocalDate transferDate;

    private String notes;
}
