package com.sparta.skilledproject.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCommentDto {

    @NotBlank
    private String content;

}
