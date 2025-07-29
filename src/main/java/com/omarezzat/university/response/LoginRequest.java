package com.omarezzat.university.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "UserName Should be not Empty")
    private String userName;
    @NotBlank
    private String password;
    private String role;
}