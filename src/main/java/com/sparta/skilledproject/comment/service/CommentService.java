package com.sparta.skilledproject.comment.service;

import com.sparta.skilledproject.comment.dto.CreateCommentDto;
import com.sparta.skilledproject.comment.dto.ResponseCommentDto;
import com.sparta.skilledproject.comment.dto.ResponseDeleteDto;
import com.sparta.skilledproject.comment.dto.UpdateCommentDto;
import jakarta.servlet.http.HttpServletRequest;

public interface CommentService {

    public ResponseCommentDto createComment(Long postId, CreateCommentDto createCommentDto, HttpServletRequest request);

    ResponseCommentDto updateCommentById(Long commentId, UpdateCommentDto updateCommentDto, HttpServletRequest request);

    ResponseDeleteDto deleteCommentById(Long commentId, HttpServletRequest request);
}
