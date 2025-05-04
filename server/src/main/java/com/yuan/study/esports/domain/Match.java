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
    private String description;

    public Status getStatus() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(startDate)) {
            return Status.upcoming;
        }
        if (now.isAfter(endDate)) {
            return Status.completed;
        }
        return Status.ongoing;
    }

    public Boolean canRegister() {
        return LocalDateTime.now().isBefore(startDate);
    }

    public enum Status {
        upcoming,
        ongoing,
        completed
    }
}
