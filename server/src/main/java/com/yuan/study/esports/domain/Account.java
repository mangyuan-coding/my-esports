package com.yuan.study.esports.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Boolean isAdmin;
}
