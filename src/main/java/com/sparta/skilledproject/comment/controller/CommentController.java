package com.sparta.skilledproject.comment.controller;

import com.sparta.skilledproject.comment.dto.CreateCommentDto;
import com.sparta.skilledproject.comment.dto.ResponseCommentDto;
import com.sparta.skilledproject.comment.dto.ResponseDeleteDto;
import com.sparta.skilledproject.comment.dto.UpdateCommentDto;
import com.sparta.skilledproject.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{postId}")
    public ResponseCommentDto createComment(@PathVariable Long postId,
                                            @RequestBody @Valid CreateCommentDto createCommentDto,
                                            HttpServletRequest request) {
        return commentService.createComment(postId, createCommentDto, request);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseCommentDto updateCommentById(@PathVariable Long commentId,
                                                @RequestBody @Valid UpdateCommentDto updateCommentDto,
                                                HttpServletRequest request) {
        return commentService.updateCommentById(commentId, updateCommentDto, request);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseDeleteDto deleteCommentById(@PathVariable Long commentId, HttpServletRequest request) {
        return commentService.deleteCommentById(commentId, request);
    }

}
