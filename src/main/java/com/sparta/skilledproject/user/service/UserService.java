package com.sparta.skilledproject.user.service;

import com.sparta.skilledproject.user.dto.LoginRequestDto;
import com.sparta.skilledproject.user.dto.ResponseAuthDto;
import com.sparta.skilledproject.user.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    ResponseAuthDto signup(SignupRequestDto signupRequestDto);

    ResponseAuthDto login(LoginRequestDto loginRequestDto, HttpServletResponse response);
}
