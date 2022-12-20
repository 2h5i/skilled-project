package com.sparta.skilledproject.comment.service;

import com.sparta.skilledproject.comment.dto.CommentStatus;
import com.sparta.skilledproject.comment.dto.CreateCommentDto;
import com.sparta.skilledproject.comment.dto.ResponseCommentDto;
import com.sparta.skilledproject.comment.dto.ResponseDeleteDto;
import com.sparta.skilledproject.comment.dto.UpdateCommentDto;
import com.sparta.skilledproject.comment.entity.Comment;
import com.sparta.skilledproject.comment.repository.CommentRepository;
import com.sparta.skilledproject.common.exception.InvalidTokenException;
import com.sparta.skilledproject.jwt.JwtUtil;
import com.sparta.skilledproject.post.entity.Post;
import com.sparta.skilledproject.post.repository.PostRepository;
import com.sparta.skilledproject.user.entity.User;
import com.sparta.skilledproject.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public ResponseCommentDto createComment(Long postId, CreateCommentDto createCommentDto, HttpServletRequest request) {
        String loginUsername = validateTokenAndGetLoginId(request);

        User user = userRepository.findByUsername(loginUsername).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        Comment comment = new Comment(createCommentDto, user, post);

        commentRepository.save(comment);

        return ResponseCommentDto.of(comment);
    }

    @Override
    @Transactional
    public ResponseCommentDto updateCommentById(Long commentId, UpdateCommentDto updateCommentDto, HttpServletRequest request) {
        String loginUsername = validateTokenAndGetLoginId(request);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 댓글이 없습니다.")
        );
        User user = userRepository.findByUsername(loginUsername).orElseThrow(
                () -> new IllegalArgumentException("해당하는 사용자가 없습니다.")
        );

        validateComment(comment, user);
        comment.updateComment(updateCommentDto);
        commentRepository.saveAndFlush(comment);

        return ResponseCommentDto.of(comment);
    }

    @Override
    @Transactional
    public ResponseDeleteDto deleteCommentById(Long commentId, HttpServletRequest request) {
        String loginUsername = validateTokenAndGetLoginId(request);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 댓글이 없습니다.")
        );

        User user = userRepository.findByUsername(loginUsername).orElseThrow(
                () -> new IllegalArgumentException("해당하는 사용자가 없습니다.")
        );

        validateComment(comment, user);
        commentRepository.deleteById(commentId);

        return new ResponseDeleteDto(CommentStatus.DELETE_SUCCESS);
    }

    private String validateTokenAndGetLoginId(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new InvalidTokenException();
            }
        } else {
            throw new InvalidTokenException();
        }

        return claims.getSubject();
    }

    private void validateComment(Comment comment, User user) {
        if(!user.isAdmin() && !user.hasComment(comment)) {
            throw new IllegalArgumentException("해당 작성자의 댓글이 아닙니다.");
        }
    }
}
