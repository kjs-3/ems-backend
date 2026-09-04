package com.example.employeemanagementSystem.authcontroller;

import com.example.employeemanagementSystem.authservice.AuthService;
import com.example.employeemanagementSystem.incomingrequests.LoginRequest;
import com.example.employeemanagementSystem.incomingrequests.RegisterRequest;
import com.example.employeemanagementSystem.outgoingresponse.JwtResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok(Map.of("message","Successfully Registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
