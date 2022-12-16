package com.sparta.skilledproject.common.handler.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int statusCode;
    private String errorCode;
    private String errorMsg;

    public ErrorResponse(ErrorCode errorCode, String errorMsg) {
        this.statusCode = errorCode.getStatusCode();
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorMsg;
    }

    public static ErrorResponse of(ErrorCode errorCode, String errorMsg) {
        return new ErrorResponse(errorCode , errorMsg);
    }
}
