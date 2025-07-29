package com.omarezzat.university.service.auth;


import com.omarezzat.university.response.LoginRequest;
import com.omarezzat.university.response.LoginResponse;

public interface IAuthService {
    public LoginResponse authenticateUser(LoginRequest loginRequest);
    }
