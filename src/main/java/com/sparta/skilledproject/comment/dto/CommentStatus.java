package com.sparta.skilledproject.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentStatus {
    DELETE_SUCCESS(200, "댓글 삭제 성공");

    private int statusCode;
    private String msg;

}
