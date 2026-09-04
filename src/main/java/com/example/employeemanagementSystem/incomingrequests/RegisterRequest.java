package com.example.employeemanagementSystem.incomingrequests;

import com.example.employeemanagementSystem.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    //username,userpassword,userRole
    @NotBlank
    private String userName;
    @NotBlank
    private String userPassword;
    @NotNull
    private Role role;
}
