package com.yuan.study.esports.domain;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRecord {

    private Long clubId;

    private Long matchId;

    private LocalDateTime registerDate;
}
