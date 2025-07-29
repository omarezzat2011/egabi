package com.omarezzat.university;

import com.omarezzat.university.controller.AuthController;
import com.omarezzat.university.response.ApiResponse;
import com.omarezzat.university.response.LoginRequest;
import com.omarezzat.university.response.LoginResponse;
import com.omarezzat.university.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    public AuthControllerTest() {
        openMocks(this);
    }

    @Test
    void login_withValidStudentRole_shouldReturnOk() {
        LoginRequest request = new LoginRequest("student@example.com", "pass", "Student");
        Object expectedResponse = "token";

        when(authService.authenticateUser(request)).thenReturn((LoginResponse) expectedResponse);

        ResponseEntity<?> response = authController.login(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void login_withValidAdminRole_shouldReturnOk() {
        LoginRequest request = new LoginRequest("admin@example.com", "pass", "Admin");
        Object expectedResponse = "token";

        when(authService.authenticateUser(request)).thenReturn((LoginResponse) expectedResponse);

        ResponseEntity<?> response = authController.login(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void login_withInvalidRole_shouldReturnForbidden() {
        LoginRequest request = new LoginRequest("user@example.com", "pass", "Guest");

        ResponseEntity<?> response = authController.login(request);

        assertEquals(403, response.getStatusCodeValue());
        ApiResponse body = (ApiResponse) response.getBody();
        assertEquals("Invalid role", body.getMessage());
    }

    @Test
    void login_withBadCredentials_shouldReturnForbidden() {
        LoginRequest request = new LoginRequest("user@example.com", "pass", "Student");

        when(authService.authenticateUser(request)).thenThrow(new AuthenticationException("Bad credentials") {});

        ResponseEntity<?> response = authController.login(request);

        assertEquals(403, response.getStatusCodeValue());
        ApiResponse body = (ApiResponse) response.getBody();
        assertEquals("Bad credentials", body.getMessage());
    }

    @Test
    void login_withUnhandledException_shouldReturnInternalServerError() {
        LoginRequest request = new LoginRequest("user@example.com", "pass", "Student");

        when(authService.authenticateUser(request)).thenThrow(new RuntimeException("Something went wrong"));

        ResponseEntity<?> response = authController.login(request);

        assertEquals(500, response.getStatusCodeValue());
        ApiResponse body = (ApiResponse) response.getBody();
        assertEquals("Error", body.getMessage());
        assertEquals("Something went wrong", body.getData());
    }
}

