package com.yuan.study.esports.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    private Long id;

    private Long playerId;

    private Long toClubId;

    private BigDecimal transferFee;

    private LocalDate transferDate;

    private String notes;
}
