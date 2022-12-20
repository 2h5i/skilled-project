package com.sparta.skilledproject.comment.dto;

import com.sparta.skilledproject.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseCommentDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;

    public ResponseCommentDto (Comment comment) {
        this.id = comment.getId();
        this.content  = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUser().getUsername();
    }

    public static ResponseCommentDto of(Comment comment) {
        return new ResponseCommentDto(comment);
    }

    public static List<ResponseCommentDto> of (List<Comment> comments) {
        return comments.stream().map(ResponseCommentDto::of).collect(Collectors.toList());
    }

}
