package com.sparta.skilledproject.common.handler.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_TOKEN(400, "9000", "올바른 토큰이 아닙니다."),
    NO_EXIST_USER(400, "9001", "존재하지 않는 사용자입니다."),
    UNAUTHORIZED(400, "9002","본인이 작성한 게시물이 아닙니다."),
    ILLEGAL_ARGUMENT(400, "9003", "");


    private final int statusCode;
    private final String errorCode;
    private final String errorMsg;

    ErrorCode(int statusCode, String errorCode, String errorMsg) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
