package com.sparta.skilledproject.post.dto;

public class ResponseDeleteDto {
    private String msg;
    private int statusCode;

    public ResponseDeleteDto(PostStatus status){
        this.msg = status.getMsg();
        this.statusCode = status.getStatusCode();
    }
}
