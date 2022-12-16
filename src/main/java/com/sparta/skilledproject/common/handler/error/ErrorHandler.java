package com.sparta.skilledproject.common.handler.error;

import com.sparta.skilledproject.common.exception.InvalidTokenException;
import com.sparta.skilledproject.common.exception.NoExistUserException;
import com.sparta.skilledproject.common.exception.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleInvalidTokenException() {
        return ErrorResponse.of(ErrorCode.INVALID_TOKEN, ErrorCode.INVALID_TOKEN.getErrorMsg());
    }

    @ExceptionHandler(NoExistUserException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleNoExistUserException() {
        return ErrorResponse.of(ErrorCode.NO_EXIST_USER, ErrorCode.NO_EXIST_USER.getErrorMsg());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleUnAuthorizedException() {
        return ErrorResponse.of(ErrorCode.UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getErrorMsg());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception) {
        return ErrorResponse.of(ErrorCode.ILLEGAL_ARGUMENT, exception.getMessage());
    }
}
