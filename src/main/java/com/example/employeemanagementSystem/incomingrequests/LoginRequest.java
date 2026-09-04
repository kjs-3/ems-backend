package com.example.employeemanagementSystem.incomingrequests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginRequest {
    //username,userpassword
    @NotBlank
    private String userName;
    @NotBlank
    private String userPassword;
}
