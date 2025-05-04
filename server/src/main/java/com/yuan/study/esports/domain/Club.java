package com.yuan.study.esports.domain;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    private Long id;

    private String name;

    private String location;

    private String coach;

    private LocalDate establishedDate;
}
