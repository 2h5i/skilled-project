package com.sparta.skilledproject.comment.dto;

import lombok.Getter;

@Getter
public class ResponseDeleteDto {
    private String msg;
    private int statusCode;

    public ResponseDeleteDto(CommentStatus status){
        this.msg = status.getMsg();
        this.statusCode = status.getStatusCode();
    }
}
