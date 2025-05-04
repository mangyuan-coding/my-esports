package com.yuan.study.esports.domain;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    private Long id;

    private String name;

    private Game game;

    private String position;

    private Long clubId;

    private LocalDate joinDate;

    private String bio;
}
