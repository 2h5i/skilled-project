package com.sparta.skilledproject.post.dto;

import com.sparta.skilledproject.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponsePostDto {

    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ResponsePostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public static ResponsePostDto of(Post post) {
        return new ResponsePostDto(post);
    }

    public static List<ResponsePostDto> of(List<Post> posts) {
        return posts.stream().map(ResponsePostDto::of).collect(Collectors.toList());
    }
}
