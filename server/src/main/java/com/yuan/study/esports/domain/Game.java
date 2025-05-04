package com.yuan.study.esports.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Game {
    LOL("英雄联盟"),
    CSGO("CS:GO"),
    Overwatch("守望先锋"),
    Valorant("无畏契约");

    @Getter
    private final String label;
}
