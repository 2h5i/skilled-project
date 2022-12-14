package com.sparta.skilledproject.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePostDto {

    private String title;
    private String writer;
    private String contents;
    private String password;

}
