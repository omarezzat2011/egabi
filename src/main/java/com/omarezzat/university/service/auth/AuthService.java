package com.omarezzat.university.service.auth;


import com.omarezzat.university.response.LoginRequest;
import com.omarezzat.university.response.LoginResponse;
import com.omarezzat.university.security.jwt.JwtUtils;
import com.omarezzat.university.security.user.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        if (!loginRequest.getRole().equalsIgnoreCase(userDetails.getRole())) {
            throw new BadCredentialsException("Role mismatch");
        }
        String jwtToken = jwtUtils.generateTokenForUser(authentication);
        return new LoginResponse(userDetails.getUsername(), jwtToken);
    }

}
