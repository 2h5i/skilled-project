package com.sparta.skilledproject.post.service;

import com.sparta.skilledproject.common.exception.InvalidTokenException;
import com.sparta.skilledproject.common.exception.NoExistUserException;
import com.sparta.skilledproject.common.exception.UnauthorizedException;
import com.sparta.skilledproject.jwt.JwtUtil;
import com.sparta.skilledproject.post.dto.CreatePostDto;
import com.sparta.skilledproject.post.dto.PostStatus;
import com.sparta.skilledproject.post.dto.ResponseDeleteDto;
import com.sparta.skilledproject.post.dto.ResponsePostDto;
import com.sparta.skilledproject.post.dto.UpdatePostDto;
import com.sparta.skilledproject.post.entity.Post;
import com.sparta.skilledproject.post.repository.PostRepository;
import com.sparta.skilledproject.user.entity.User;
import com.sparta.skilledproject.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional(readOnly = true)
    public List<ResponsePostDto> getPostList() {
        return ResponsePostDto.of(postRepository.findAllByOrderByCreatedAtDesc());
    }

    @Override
    @Transactional
    public ResponsePostDto createPost(CreatePostDto createPostDto, HttpServletRequest request) {
        String loginUsername = validateTokenAndGetLoginId(request);

        User user = userRepository.findByUsername(loginUsername).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        Post post = new Post(createPostDto, user);

        postRepository.save(post);
        return ResponsePostDto.of(post);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponsePostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        return ResponsePostDto.of(post);
    }

    @Override
    @Transactional
    public ResponsePostDto updatePostById(Long id, UpdatePostDto updatePostDto, HttpServletRequest request) {
        String loginUsername = validateTokenAndGetLoginId(request);

        User user = userRepository.findByUsername(loginUsername).orElseThrow(NoExistUserException::new);

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        validateAuthorization(user, post);
        post.update(updatePostDto);
        postRepository.saveAndFlush(post);
        return ResponsePostDto.of(post);
    }

    @Override
    @Transactional
    public ResponseDeleteDto deletePostById(Long id, HttpServletRequest request) {
        String loginUsername = validateTokenAndGetLoginId(request);
        User user = userRepository.findByUsername(loginUsername).orElseThrow(NoExistUserException::new);

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        validateAuthorization(user, post);
        postRepository.deleteById(id);
        return new ResponseDeleteDto(PostStatus.DELETE_SUCCESS);

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

    private void validateAuthorization(User user, Post post) {
        if (!user.hasPost(post) && !user.isAdmin()) {
            throw new UnauthorizedException();
        }
    }
}
