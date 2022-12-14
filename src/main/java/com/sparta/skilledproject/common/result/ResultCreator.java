package com.sparta.skilledproject.common.result;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class ResultCreator {

    public <T> Result getSuccessDataResult(int code, String msg, T data) {
        return DataResult.builder()
                .success(true)
                .message(msg)
                .statusCode(code)
                .data(data)
                .build();
    }

    public Result getFailureResult(int code, String msg) {
        return Result.baseBuilder()
                .success(false)
                .message(msg)
                .statusCode(code)
                .build();
    }

    public Result getSuccessResult(int code , String msg) {
        return Result.baseBuilder()
                .success(true)
                .message(msg)
                .statusCode(code)
                .build();
    }
}
