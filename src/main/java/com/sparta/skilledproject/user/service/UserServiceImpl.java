package com.sparta.skilledproject.user.service;

import com.sparta.skilledproject.jwt.JwtUtil;
import com.sparta.skilledproject.user.dto.LoginRequestDto;
import com.sparta.skilledproject.user.dto.ResponseAuthDto;
import com.sparta.skilledproject.user.dto.SignupRequestDto;
import com.sparta.skilledproject.user.dto.AuthStatus;
import com.sparta.skilledproject.user.entity.User;
import com.sparta.skilledproject.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public ResponseAuthDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);
        return new ResponseAuthDto(AuthStatus.SIGNUP_SUCCESS);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseAuthDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        return new ResponseAuthDto(AuthStatus.LOGIN_SUCCESS);

    }
}
