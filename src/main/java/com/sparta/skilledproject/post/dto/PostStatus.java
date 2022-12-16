package com.sparta.skilledproject.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostStatus {

    DELETE_SUCCESS(200, "게시글 삭제 성공");

    private int statusCode;
    private String msg;

}
