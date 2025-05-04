package com.yuan.study.esports.controller.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private static final int SUCCESS_CODE = 0;

    private int code;

    private T data;

    private String msg;

    public static <T> Response<T> ofSuccess(T data) {
        return new Response<>(SUCCESS_CODE, data, "");
    }

    public static <T> Response<T> ofSuccess() {
        return new Response<>(SUCCESS_CODE, null, "");
    }
}
