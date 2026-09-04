package com.example.employeemanagementSystem.outgoingresponse;

import com.example.employeemanagementSystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    //username,role,token
    private String userName;

    private Role role;
    private String token;
}
