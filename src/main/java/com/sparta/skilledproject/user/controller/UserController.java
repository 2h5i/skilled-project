package com.sparta.skilledproject.user.controller;

import com.sparta.skilledproject.user.dto.LoginRequestDto;
import com.sparta.skilledproject.user.dto.ResponseAuthDto;
import com.sparta.skilledproject.user.dto.SignupRequestDto;
import com.sparta.skilledproject.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseAuthDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseAuthDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequestDto,response));
    }
}
