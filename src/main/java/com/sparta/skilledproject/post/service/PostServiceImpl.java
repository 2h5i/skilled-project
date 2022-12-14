package com.sparta.skilledproject.post.service;

import com.sparta.skilledproject.common.exception.InvalidTokenException;
import com.sparta.skilledproject.jwt.JwtUtil;
import com.sparta.skilledproject.post.dto.CreatePostDto;
import com.sparta.skilledproject.post.dto.DeletePostDto;
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

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public List<ResponsePostDto> getPostList() {
        return ResponsePostDto.of(postRepository.findAllByOrderByCreatedAtDesc());
    }

    @Override
    public ResponsePostDto createPost(CreatePostDto createPostDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token!=null) {
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            }else {
                throw new InvalidTokenException("올바른 토큰이 아닙니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = new Post(createPostDto, user);

            postRepository.save(post);

            return ResponsePostDto.of(post);
        }else {
            throw new InvalidTokenException("토큰이 존재하지 않습니다.");
        }

    }

    @Override
    public ResponsePostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        return ResponsePostDto.of(post);
    }

    @Override
    public ResponsePostDto updatePostById(Long id, UpdatePostDto updatePostDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        if(post.isCorrectPassword(updatePostDto.getPassword())){
            post.update(updatePostDto);
            Post editedPost = postRepository.save(post);

            return ResponsePostDto.of(editedPost);
        }else {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }

    }

    @Override
    public HashMap<String,String> deletePostById(Long id, DeletePostDto deletePostDto) {
        // HTTPSTATUS.OK 반환할수도 있음
        HashMap<String , String> response= new HashMap<String,String>();
        response.put("status", "ok");
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        if(post.isCorrectPassword(deletePostDto.getPassword())){
            postRepository.deleteById(id);
            return response;
        } else {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }
}
