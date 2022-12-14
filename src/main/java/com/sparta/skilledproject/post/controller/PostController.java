package com.sparta.skilledproject.post.controller;

import com.sparta.skilledproject.post.dto.CreatePostDto;
import com.sparta.skilledproject.post.dto.DeletePostDto;
import com.sparta.skilledproject.post.dto.ResponsePostDto;
import com.sparta.skilledproject.post.dto.UpdatePostDto;
import com.sparta.skilledproject.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponsePostDto createPost(@RequestBody CreatePostDto createPostDto, HttpServletRequest request) {
        return postService.createPost(createPostDto, request);
    }

    @GetMapping("/posts")
    public List<ResponsePostDto> getPosts() {
        return postService.getPostList();
    }

    @GetMapping("/posts/{id}")
    public ResponsePostDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/posts/{id}")
    public ResponsePostDto updatePostById(@PathVariable Long id, @RequestBody UpdatePostDto updatePostDto) {
        return postService.updatePostById(id, updatePostDto);
    }

    @DeleteMapping("/posts/{id}")
    public HashMap<String, String> deletePostById(@PathVariable Long id, @RequestBody DeletePostDto deletePostDto) {
        return postService.deletePostById(id, deletePostDto);
    }

}
