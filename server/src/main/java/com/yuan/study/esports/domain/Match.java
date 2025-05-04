package com.yuan.study.esports.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    private Long id;
    private String name;
    private Game game;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal prizePool;
    private String location;
    private Status status;
    private String description;

    public enum Status {
        upcoming,
        ongoing,
        completed
    }
}
