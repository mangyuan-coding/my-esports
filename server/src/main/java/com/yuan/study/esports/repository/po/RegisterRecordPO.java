package com.yuan.study.esports.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_register_record")
public class RegisterRecordPO {
    @TableId
    private Long id;

    private Long clubId;

    private Long matchId;

    private LocalDateTime registerDate;
}
