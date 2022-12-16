package com.sparta.skilledproject.common.exception;

public class NoExistUserException extends RuntimeException {
    public NoExistUserException() {
        super();
    }

    public NoExistUserException(String message) {
        super(message);
    }

    public NoExistUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
