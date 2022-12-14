package com.sparta.skilledproject.post.service;

import com.sparta.skilledproject.post.dto.CreatePostDto;
import com.sparta.skilledproject.post.dto.DeletePostDto;
import com.sparta.skilledproject.post.dto.ResponsePostDto;
import com.sparta.skilledproject.post.dto.UpdatePostDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

public interface PostService {

    List<ResponsePostDto> getPostList();

    ResponsePostDto createPost(CreatePostDto createPostDto, HttpServletRequest request);

    ResponsePostDto getPostById(Long id);

    ResponsePostDto updatePostById(Long id, UpdatePostDto updatePostDto);

    HashMap<String,String> deletePostById(Long id, DeletePostDto deletePostDto);
}
