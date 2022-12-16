package com.sparta.skilledproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthStatus {

    SIGNUP_SUCCESS(200, "회원 가입 성공"),
    LOGIN_SUCCESS(200, "로그인 성공");

    private int statusCode;
    private String msg;

}
