package com.sparta.skilledproject.user.dto;

import lombok.Getter;

@Getter
public class ResponseAuthDto {

    private String msg;
    private int statusCode;

    public ResponseAuthDto(AuthStatus status){
        this.msg = status.getMsg();
        this.statusCode = status.getStatusCode();
    }
}
