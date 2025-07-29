package com.omarezzat.university.controller;


import com.omarezzat.university.response.ApiResponse;
import com.omarezzat.university.response.LoginRequest;
import com.omarezzat.university.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            if (loginRequest.getRole().equalsIgnoreCase("Student"))
                return ResponseEntity.ok().body(authService.authenticateUser(loginRequest));
            if (loginRequest.getRole().equalsIgnoreCase("Admin"))
                return ResponseEntity.ok().body(authService.authenticateUser(loginRequest));

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse("Invalid role", null));
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(FORBIDDEN).body(new ApiResponse("Bad credentials", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", e.getMessage()));
        }
    }

}
